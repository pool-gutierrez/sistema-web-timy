package com.pe.timy.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.VentaProducto;
import com.pe.timy.repository.VentaProductoRepository;
import com.pe.timy.service.VentaProductoService;
import com.pe.timy.util.clases.Salida;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

	@Autowired
	private VentaProductoRepository ventaProductoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<VentaProducto> findAll() {
		return ventaProductoRepository.findAll();
	}
	
	@Override
	@Transactional
	public void save(VentaProducto ventaProducto) {
		ventaProductoRepository.save(ventaProducto);
	}
	
	@Override
	public List<Salida> findProductosPorFecha(Integer productoId) {
		List<Object[]> results = ventaProductoRepository.findTotalProductosVendidosPorDia(productoId);
		List<Salida> salidas = new ArrayList<>();
        for (Object[] result : results) {
            LocalDate fecha = (LocalDate) result[0];
            Long cantidadTotal = (Long) result[1];
            Integer valorInt = Math.toIntExact(cantidadTotal);
            salidas.add(new Salida(fecha, valorInt));
        }
        return salidas;
	}
}
