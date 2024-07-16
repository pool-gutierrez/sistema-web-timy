package com.pe.timy.util;

public class ProductoMapper {

	private Integer id;
	private String nombre;
	private String categoria;
	private Double precioVenta;
	private Integer stock;
	
	public ProductoMapper() {
	}
	
	public ProductoMapper(Integer id, String nombre, String categoria, Double precioVenta, Integer stock) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precioVenta = precioVenta;
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
