package com.msw.ventas.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.msw.ventas.model.Venta;

public interface VentaDaoApi extends CrudRepository<Venta, Long> {

}