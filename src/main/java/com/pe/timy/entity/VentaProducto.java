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
@Table(name = "venta_producto")
public class VentaProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ventaProductoId;
	private Double precioVenta;
	private Integer cantidad;
	@ManyToOne
	@JoinColumn(name = "venta_id")
	private Venta venta;
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	public VentaProducto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getVentaProductoId() {
		return ventaProductoId;
	}

	public void setVentaProductoId(Integer ventaProductoId) {
		this.ventaProductoId = ventaProductoId;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
