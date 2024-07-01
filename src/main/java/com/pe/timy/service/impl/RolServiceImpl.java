package com.pe.timy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Rol;
import com.pe.timy.repository.RolRepository;
import com.pe.timy.service.RolService;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}
}
