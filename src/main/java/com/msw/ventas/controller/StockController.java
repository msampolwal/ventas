package com.msw.ventas.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msw.ventas.model.Producto;
import com.msw.ventas.service.api.ProductoServiceApi;

@Controller
public class StockController {
	
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