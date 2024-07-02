package com.pe.timy.service;

import java.util.List;

import com.pe.timy.entity.CotizacionProducto;

public interface CotizacionProductoService {

	List<CotizacionProducto> findAll();
	void save(CotizacionProducto cotizacionProducto);
}
