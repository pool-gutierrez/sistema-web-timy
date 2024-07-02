package com.pe.timy.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer inventarioId;
	private Integer entradas;
	private Integer salidas;
	private Integer stockActual;
	private Integer stockMinimo;
	private LocalDate fechaActualizacion;
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	public Inventario() {
		// TODO Auto-generated constructor stub
	}

	public Integer getInventarioId() {
		return inventarioId;
	}

	public void setInventarioId(Integer inventarioId) {
		this.inventarioId = inventarioId;
	}

	public Integer getEntradas() {
		return entradas;
	}

	public void setEntradas(Integer entradas) {
		this.entradas = entradas;
	}

	public Integer getSalidas() {
		return salidas;
	}

	public void setSalidas(Integer salidas) {
		this.salidas = salidas;
	}

	public Integer getStockActual() {
		return stockActual;
	}

	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}

	public Integer getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public LocalDate getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
