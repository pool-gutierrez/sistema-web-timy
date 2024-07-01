package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Cliente;

public interface ClienteService {

	List<Cliente> findAll();
	List<Cliente> findAllActive();
	Optional<Cliente> findById(Integer clienteId);
	void save(Cliente cliente);
}
