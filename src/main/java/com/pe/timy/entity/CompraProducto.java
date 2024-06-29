package com.pe.timy.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra_producto")
public class CompraProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer compraProductoId;
	private Double precioCompra;
	private Integer cantidad;
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	public CompraProducto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCompraProductoId() {
		return compraProductoId;
	}

	public void setCompraProductoId(Integer compraProductoId) {
		this.compraProductoId = compraProductoId;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
