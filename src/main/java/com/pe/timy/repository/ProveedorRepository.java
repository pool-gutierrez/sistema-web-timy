package com.pe.timy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pe.timy.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

	List<Proveedor> findAllByEstado(Boolean estado);
}
