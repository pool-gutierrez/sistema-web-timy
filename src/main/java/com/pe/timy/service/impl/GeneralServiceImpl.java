package com.pe.timy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pe.timy.entity.Inventario;
import com.pe.timy.service.GeneralService;
import com.pe.timy.service.InventarioService;
import com.pe.timy.util.ProductoMapper;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private InventarioService inventarioService;

	@Override
	public String encryptPassword(String contrasena) {
		return bCryptPasswordEncoder.encode(contrasena);
	}

	@Override
	public List<ProductoMapper> mapperList() {
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
}