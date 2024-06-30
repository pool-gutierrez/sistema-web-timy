package com.pe.timy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pe.timy.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	List<Producto> findAllByEstado(Boolean estado);
}
