package com.pe.timy.service;

import java.util.List;

import com.pe.timy.entity.CompraProducto;

public interface CompraProductoService {

	List<CompraProducto> findAll();
	void save(CompraProducto compraProducto);
}
