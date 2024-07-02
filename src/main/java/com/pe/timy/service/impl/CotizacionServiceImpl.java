package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Cotizacion;
import com.pe.timy.repository.CotizacionRepository;
import com.pe.timy.service.CotizacionService;

@Service
public class CotizacionServiceImpl implements CotizacionService {

	@Autowired
	private CotizacionRepository cotizacionRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cotizacion> findAll() {
		return cotizacionRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Cotizacion> findById(Integer cotizacionId) {
		return cotizacionRepository.findById(cotizacionId);
	}
	
	@Override
	@Transactional
	public void save(Cotizacion contizacion) {
		cotizacionRepository.save(contizacion);
	}
}
