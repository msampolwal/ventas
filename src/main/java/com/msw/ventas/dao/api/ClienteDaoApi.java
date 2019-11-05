package com.msw.ventas.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.msw.ventas.model.Cliente;

public interface ClienteDaoApi extends CrudRepository<Cliente, Long> {

}