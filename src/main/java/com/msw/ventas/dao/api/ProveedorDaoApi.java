package com.msw.ventas.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.msw.ventas.model.Proveedor;

public interface ProveedorDaoApi extends CrudRepository<Proveedor, Long> {

}