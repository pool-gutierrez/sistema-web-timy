package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Categoria;

public interface CategoriaService {

	List<Categoria> findAll();
	List<Categoria> findAllActive();
	Optional<Categoria> findById(Integer categoriaId);
	void save(Categoria categoria);
	
}
