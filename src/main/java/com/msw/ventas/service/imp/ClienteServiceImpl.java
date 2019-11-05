package com.msw.ventas.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.msw.ventas.commons.GenericServiceImp;
import com.msw.ventas.dao.api.ClienteDaoApi;
import com.msw.ventas.model.Cliente;
import com.msw.ventas.service.api.ClienteServiceApi;

@Service
public class ClienteServiceImpl extends GenericServiceImp<Cliente, Long> implements ClienteServiceApi{

	@Autowired(required = true)
	private ClienteDaoApi clienteDao;
	
	@Override
	public CrudRepository<Cliente, Long> getDao() {
		return clienteDao;
	}
}
