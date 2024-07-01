package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Empleado;

public interface EmpleadoService {

	List<Empleado> findAll();
	List<Empleado> findAllActive();
	Optional<Empleado> findById(Integer empleadoId);
	void save(Empleado empleado);
}
