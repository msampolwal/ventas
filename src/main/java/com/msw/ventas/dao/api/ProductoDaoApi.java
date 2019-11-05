package com.msw.ventas.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.msw.ventas.model.Producto;

public interface ProductoDaoApi extends CrudRepository<Producto, Long> {

}