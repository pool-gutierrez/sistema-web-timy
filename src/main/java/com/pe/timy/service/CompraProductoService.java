package com.pe.timy.service;

import java.util.List;

import com.pe.timy.entity.CompraProducto;
import com.pe.timy.util.clases.Entrada;

public interface CompraProductoService {

	List<CompraProducto> findAll();

	void save(CompraProducto compraProducto);

	List<Entrada> findProductosPorFecha(Integer productoId);
}
