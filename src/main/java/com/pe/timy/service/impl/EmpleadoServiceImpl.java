package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Empleado;
import com.pe.timy.repository.EmpleadoRepository;
import com.pe.timy.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAllActive() {
		return empleadoRepository.findAllByEstado(true);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Empleado> findById(Integer empleadoId) {
		return empleadoRepository.findById(empleadoId);
	}
	
	@Override
	@Transactional
	public void save(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
}
