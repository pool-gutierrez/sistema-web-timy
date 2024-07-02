package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Inventario;
import com.pe.timy.entity.Producto;

public interface InventarioService {

	List<Inventario> findAll();
	List<Inventario> findByProductoEstadoTrue();
	Optional<Inventario> findById(Integer inventarioId);
	Optional<Inventario> findByProducto(Producto producto);
	void save(Inventario inventario);
}
