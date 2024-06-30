package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Producto;

public interface ProductoService {

	List<Producto> findAll();
	List<Producto> findAllActive();
	Optional<Producto> findById(Integer productoId);
	void save(Producto producto);
}
