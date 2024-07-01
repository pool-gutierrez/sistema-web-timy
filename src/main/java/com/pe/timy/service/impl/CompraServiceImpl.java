package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Compra;
import com.pe.timy.repository.CompraRepository;
import com.pe.timy.service.CompraService;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Compra> findAll() {
		return compraRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Compra> findById(Integer compraId) {
		return compraRepository.findById(compraId);
	}
	
	@Override
	@Transactional
	public void save(Compra compra) {
		compraRepository.save(compra);
	}
}
