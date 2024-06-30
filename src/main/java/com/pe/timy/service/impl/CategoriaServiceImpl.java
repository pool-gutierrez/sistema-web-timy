package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Categoria;
import com.pe.timy.repository.CategoriaRepository;
import com.pe.timy.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAllActive() {
		return categoriaRepository.findAllByEstado(true);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Categoria> findById(Integer categoriaId) {
		return categoriaRepository.findById(categoriaId);
	}
	
	@Override
	@Transactional
	public void save(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
}
