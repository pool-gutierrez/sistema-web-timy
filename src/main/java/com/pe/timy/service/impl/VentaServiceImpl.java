package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Venta;
import com.pe.timy.repository.VentaRepository;
import com.pe.timy.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Venta> findAll() {
		return ventaRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Venta> findById(Integer ventaId) {
		return ventaRepository.findById(ventaId);
	}
	
	@Override
	@Transactional
	public void save(Venta venta) {
		ventaRepository.save(venta);
	}
}
