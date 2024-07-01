package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Cliente;
import com.pe.timy.repository.ClienteRepository;
import com.pe.timy.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAllActive() {
		return clienteRepository.findAllByEstado(true);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Integer clienteId) {
		return clienteRepository.findById(clienteId);
	}
	
	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}
}
