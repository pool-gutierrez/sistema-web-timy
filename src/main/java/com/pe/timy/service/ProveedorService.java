package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Proveedor;

public interface ProveedorService {

	List<Proveedor> findAll();
	List<Proveedor> findAllActive();
	Optional<Proveedor> findById(Integer proveedorId);
	void save(Proveedor proveedor);
}
