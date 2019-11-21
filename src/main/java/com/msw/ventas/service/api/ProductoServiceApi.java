package com.msw.ventas.service.api;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msw.ventas.commons.GenericServiceApi;
import com.msw.ventas.model.Producto;

public interface ProductoServiceApi extends GenericServiceApi<Producto, Long> {

	boolean createExcel(List<Producto> productos, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

}
