package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Inventario;
import com.pe.timy.entity.Producto;
import com.pe.timy.repository.InventarioRepository;
import com.pe.timy.service.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {

	@Autowired
	private InventarioRepository inventarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Inventario> findAll() {
		return inventarioRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Inventario> findByProductoEstadoTrue() {
		return inventarioRepository.findByProductoEstadoTrue();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Inventario> findById(Integer inventarioId) {
		return inventarioRepository.findById(inventarioId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Inventario> findByProducto(Producto producto) {
		return inventarioRepository.findByProducto(producto);
	}
	
	@Override
	@Transactional
	public void save(Inventario inventario) {
		inventarioRepository.save(inventario);
	}
}
