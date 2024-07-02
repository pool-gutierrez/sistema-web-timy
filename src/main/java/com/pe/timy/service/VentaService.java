package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Venta;

public interface VentaService {

	List<Venta> findAll();
	Optional<Venta> findById(Integer ventaId);
	void save(Venta venta);
}
