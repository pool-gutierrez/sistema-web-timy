package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Proveedor;
import com.pe.timy.repository.ProveedorRepository;
import com.pe.timy.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		return proveedorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAllActive() {
		return proveedorRepository.findAllByEstado(true);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Proveedor> findById(Integer proveedorId) {
		return proveedorRepository.findById(proveedorId);
	}

	@Override
	@Transactional
	public void save(Proveedor proveedor) {
		proveedorRepository.save(proveedor);
	}

}
