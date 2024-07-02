package com.pe.timy.service;

import java.util.List;
import java.util.Optional;

import com.pe.timy.entity.Cotizacion;

public interface CotizacionService {

	List<Cotizacion> findAll();
	Optional<Cotizacion> findById(Integer cotizacionId);
	void save(Cotizacion contizacion);
}
