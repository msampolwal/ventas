package com.msw.ventas.service.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.msw.ventas.commons.GenericServiceImp;
import com.msw.ventas.dao.api.ProductoDaoApi;
import com.msw.ventas.model.Producto;
import com.msw.ventas.service.api.ProductoServiceApi;

@Service
public class ProductoServiceImpl extends GenericServiceImp<Producto, Long> implements ProductoServiceApi {

	@Autowired
	private ProductoDaoApi productoDao;
	
	@Override
	public CrudRepository<Producto, Long> getDao() {
		return productoDao;
	}

	@Override
	public boolean createExcel(List<Producto> productos, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists)
			new File(filePath).mkdirs();
		
		try {
			FileOutputStream outputStream = new FileOutputStream(file+"/stock.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet workSheet = workbook.createSheet("Stock");
			workSheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFRow headerRow = workSheet.createRow(0);
			
			HSSFCell cellId = headerRow.createCell(0);
			cellId.setCellValue("Id");
			cellId.setCellStyle(headerCellStyle);
			
			HSSFCell cellProveedor = headerRow.createCell(1);
			cellProveedor.setCellValue("Proveedor");
			cellProveedor.setCellStyle(headerCellStyle);
			
			HSSFCell cellNombre = headerRow.createCell(2);
			cellNombre.setCellValue("Nombre");
			cellNombre.setCellStyle(headerCellStyle);
			
			HSSFCell cellStock = headerRow.createCell(3);
			cellStock.setCellValue("Stock");
			cellStock.setCellStyle(headerCellStyle);
			
			for (int i = 0; i < productos.size(); i++) {
				HSSFRow bodyRow = workSheet.createRow(i+1);
				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
				
				HSSFCell cellIdValue = bodyRow.createCell(0);
				cellIdValue.setCellValue(productos.get(i).getId());
				cellIdValue.setCellStyle(bodyCellStyle);
				
				HSSFCell cellProveedorValue = bodyRow.createCell(1);
				cellProveedorValue.setCellValue(productos.get(i).getProveedor().toString());
				cellProveedorValue.setCellStyle(bodyCellStyle);
				
				HSSFCell cellNombreValue = bodyRow.createCell(2);
				cellNombreValue.setCellValue(productos.get(i).getNombre());
				cellNombreValue.setCellStyle(bodyCellStyle);
				
				HSSFCell cellStockValue = bodyRow.createCell(3);
				cellStockValue.setCellValue(productos.get(i).getStock().doubleValue());
				cellStockValue.setCellStyle(bodyCellStyle);
			}
			
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
