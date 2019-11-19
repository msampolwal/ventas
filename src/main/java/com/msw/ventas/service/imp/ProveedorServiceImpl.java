package com.msw.ventas.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.msw.ventas.commons.GenericServiceImp;
import com.msw.ventas.dao.api.ProveedorDaoApi;
import com.msw.ventas.model.Proveedor;
import com.msw.ventas.service.api.ProveedorServiceApi;

@Service
public class ProveedorServiceImpl extends GenericServiceImp<Proveedor, Long> implements ProveedorServiceApi {

	@Autowired
	private ProveedorDaoApi proveedorDao;
	
	@Override
	public CrudRepository<Proveedor, Long> getDao() {
		return proveedorDao;
	}
}
