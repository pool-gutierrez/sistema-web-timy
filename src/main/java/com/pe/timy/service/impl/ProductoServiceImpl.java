package com.pe.timy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Categoria;
import com.pe.timy.entity.Producto;
import com.pe.timy.entity.Proveedor;
import com.pe.timy.repository.ProductoRepository;
import com.pe.timy.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllActive() {
		return productoRepository.findAllByEstado(true);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Integer productoId) {
		return productoRepository.findById(productoId);
	}
	
	@Override
	@Transactional
	public void save(Producto producto) {
		productoRepository.save(producto);	
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllByProveedor(Proveedor proveedor) {
		return productoRepository.findAllByProveedorAndEstado(proveedor, true);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllByCategoria(Categoria categoria) {
		return productoRepository.findAllByCategoriaAndEstado(categoria, true);
	}
}
