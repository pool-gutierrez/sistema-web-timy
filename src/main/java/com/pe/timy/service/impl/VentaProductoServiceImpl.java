package com.pe.timy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.VentaProducto;
import com.pe.timy.repository.VentaProductoRepository;
import com.pe.timy.service.VentaProductoService;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

	@Autowired
	private VentaProductoRepository ventaProductoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<VentaProducto> findAll() {
		return ventaProductoRepository.findAll();
	}
	
	@Override
	@Transactional
	public void save(VentaProducto ventaProducto) {
		ventaProductoRepository.save(ventaProducto);
	}
}
