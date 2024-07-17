package com.pe.timy.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.CompraProducto;
import com.pe.timy.repository.CompraProductoRepository;
import com.pe.timy.service.CompraProductoService;
import com.pe.timy.util.clases.Entrada;

@Service
public class CompraProductoServiceImpl implements CompraProductoService {

	@Autowired
	private CompraProductoRepository compraProductoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CompraProducto> findAll() {
		return compraProductoRepository.findAll();
	}
	
	@Override
	@Transactional
	public void save(CompraProducto compraProducto) {
		compraProductoRepository.save(compraProducto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Entrada> findProductosPorFecha(Integer productoId) {
		List<Object[]> results = compraProductoRepository.findTotalProductosVendidosPorDia(productoId);
		List<Entrada> entradas = new ArrayList<>();
        for (Object[] result : results) {
            LocalDate fecha = (LocalDate) result[0];
            Long cantidadTotal = (Long) result[1];
            Integer valorInt = Math.toIntExact(cantidadTotal);
            entradas.add(new Entrada(fecha, valorInt));
        }
        return entradas;
	}
}
