package com.pe.timy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.CotizacionProducto;
import com.pe.timy.repository.CotizacionProductoRepository;
import com.pe.timy.service.CotizacionProductoService;

@Service
public class CotizacionProductoServiceImpl implements CotizacionProductoService {

	@Autowired
	private CotizacionProductoRepository cotizacionProductoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CotizacionProducto> findAll() {
		return cotizacionProductoRepository.findAll();
	}
	
	@Override
	@Transactional
	public void save(CotizacionProducto cotizacionProducto) {
		cotizacionProductoRepository.save(cotizacionProducto);
	}
}
