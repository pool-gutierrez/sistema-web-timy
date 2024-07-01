package com.pe.timy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pe.timy.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	List<Empleado> findAllByEstado(Boolean estado);
	Empleado findByUsuario(String Usuario);
}
