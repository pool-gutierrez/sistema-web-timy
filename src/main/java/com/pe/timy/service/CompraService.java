package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Compra;

public interface CompraService {

	List<Compra> findAll();
	Optional<Compra> findById(Integer compraId);
	void save(Compra compra);
}
