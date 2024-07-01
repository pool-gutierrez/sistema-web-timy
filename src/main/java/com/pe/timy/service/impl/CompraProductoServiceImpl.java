package com.pe.timy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.CompraProducto;
import com.pe.timy.repository.CompraProductoRepository;
import com.pe.timy.service.CompraProductoService;

@Service
public class CompraProductoServiceImpl implements CompraProductoService {

	@Autowired
	private CompraProductoRepository compraProductoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CompraProducto> findAll() {
		return compraProductoRepository.findAll();
	}
	
	@Override
	@Transactional
	public void save(CompraProducto compraProducto) {
		compraProductoRepository.save(compraProducto);
	}
}
