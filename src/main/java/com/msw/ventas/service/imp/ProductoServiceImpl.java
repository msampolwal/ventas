package com.msw.ventas.service.imp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.msw.ventas.commons.GenericServiceImp;
import com.msw.ventas.dao.api.ProductoDaoApi;
import com.msw.ventas.model.Producto;
import com.msw.ventas.service.api.ProductoServiceApi;

@Service
public class ProductoServiceImpl extends GenericServiceImp<Producto, Long> implements ProductoServiceApi {

	private ProductoDaoApi productoDao;
	
	@Override
	public CrudRepository<Producto, Long> getDao() {
		return productoDao;
	}
}
