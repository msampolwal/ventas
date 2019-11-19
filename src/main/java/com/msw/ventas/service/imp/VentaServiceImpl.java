package com.msw.ventas.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.msw.ventas.commons.GenericServiceImp;
import com.msw.ventas.dao.api.VentaDaoApi;
import com.msw.ventas.model.Venta;
import com.msw.ventas.service.api.VentaServiceApi;

@Service
public class VentaServiceImpl extends GenericServiceImp<Venta, Long> implements VentaServiceApi {

	@Autowired
	private VentaDaoApi ventaDao;
	
	@Override
	public CrudRepository<Venta, Long> getDao() {
		return ventaDao;
	}
}