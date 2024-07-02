package com.pe.timy.service;

import java.util.List;

import com.pe.timy.entity.VentaProducto;

public interface VentaProductoService {

	List<VentaProducto> findAll();
	void save(VentaProducto ventaProducto);
}
