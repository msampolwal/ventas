package com.msw.ventas.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msw.ventas.model.Producto;
import com.msw.ventas.service.api.ProductoServiceApi;

@Controller
public class StockController extends AbstractController{
	
	@Autowired
	private ProductoServiceApi productoServiceApi;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/stock/")
	public String getIndex(Model model) {
		model.addAttribute("productos", productoServiceApi.getAll());
		return "stock/list";
	}
	
	@GetMapping("/stock/createExcel")
	public void createExcel(HttpServletRequest request, HttpServletResponse response) {
		List<Producto> productos = productoServiceApi.getAll();
		boolean isFlag = productoServiceApi.createExcel(productos, context, request, response);
		
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/stock.xls");
			fileDownload(fullPath, response, "stock.xls");
		}
	}
	
	@PostMapping("/stock/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
		try {
//			OPCPackage pkg = OPCPackage.open(file.getInputStream());
			HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
		    HSSFSheet worksheet = workbook.getSheetAt(0);

		    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
		        HSSFRow row = worksheet.getRow(i);
		        
		        Producto p = productoServiceApi.get((long) row.getCell(0).getNumericCellValue());
		        p.setStock(new BigDecimal(row.getCell(3).getNumericCellValue()));
		        productoServiceApi.save(p);
		        System.out.println("Producto: " + row.getCell(0).getNumericCellValue() + " - Stock: " + row.getCell(3).getNumericCellValue());
		    }
		    info("Stock actualizado con exito", ra);
		} catch (Exception e) {
			e.printStackTrace();
			error("Error al intentar actualizar el stock", ra);
		}
		return "redirect:/stock/";
	}
	
	private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if(file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = 1;
				while ((bytesRead = inputStream.read(buffer))!=-1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}