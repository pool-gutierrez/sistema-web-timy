package com.pe.timy.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Inventario;
import com.pe.timy.service.CompraProductoService;
import com.pe.timy.service.InventarioService;
import com.pe.timy.service.VentaProductoService;
import com.pe.timy.util.GeneralService;
import com.pe.timy.util.InventarioMapper;
import com.pe.timy.util.ProductoMapper;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private InventarioService inventarioService;
	@Autowired
	private CompraProductoService compraProductoService;
	@Autowired
	private VentaProductoService ventaProductoService;

	@Override
	public String encryptPassword(String contrasena) {
		return bCryptPasswordEncoder.encode(contrasena);
	}

	@Override
	public List<ProductoMapper> productoMapper() {
		List<Inventario> inventario = inventarioService.findByProductoEstadoTrue();
		List<ProductoMapper> inventarioMapper = new ArrayList<>();
		Integer contador = 0;
		for (Inventario inventarioObject : inventario) {
			contador++;
			inventarioMapper.add(new ProductoMapper(contador, inventarioObject.getProducto().getNombre(),
					inventarioObject.getProducto().getCategoria().getNombre(),
					inventarioObject.getProducto().getPrecioVenta(), inventarioObject.getStockActual()));
		}
		return inventarioMapper;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<InventarioMapper> inventarioMappers() {
		List<Inventario> inventario = inventarioService.findByProductoEstadoTrue();
		List<InventarioMapper> inventarioMappers = new ArrayList<>();
		Integer contador = 0;
		for (Inventario inventarioObject : inventario) {
			contador++;
			InventarioMapper mapper = new InventarioMapper();
			mapper.setId(contador);
			mapper.setProducto(inventarioObject.getProducto().getNombre());
			mapper.setEntradas(compraProductoService.findProductosPorFecha(inventarioObject.getProducto().getProductoId()));
			mapper.setSalidas(ventaProductoService.findProductosPorFecha(inventarioObject.getProducto().getProductoId()));
			mapper.setStockActual(inventarioObject.getStockActual());
			mapper.setStockMinimo(inventarioObject.getStockMinimo());
			inventarioMappers.add(mapper);
		}
		return inventarioMappers;
	}
}