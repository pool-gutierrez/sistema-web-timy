package com.pe.timy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pe.timy.entity.Inventario;
import com.pe.timy.entity.Producto;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

	Optional<Inventario> findByProducto(Producto producto);
	List<Inventario> findByProductoEstadoTrue();
}
