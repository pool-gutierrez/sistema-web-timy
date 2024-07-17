package com.pe.timy.service;

import java.util.List;

import com.pe.timy.entity.VentaProducto;
import com.pe.timy.util.clases.Salida;

public interface VentaProductoService {

	List<VentaProducto> findAll();
	void save(VentaProducto ventaProducto);
	List<Salida> findProductosPorFecha(Integer productoId);
}
