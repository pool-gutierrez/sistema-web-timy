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
@Table(name = "cotizacion_producto")
public class CotizacionProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cotizacionProductoId;
	private Double precioVenta;
	private Integer cantidad;
	@ManyToOne
	@JoinColumn(name = "cotizacion_id")
	private Cotizacion cotizacion;
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	public CotizacionProducto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCotizacionProductoId() {
		return cotizacionProductoId;
	}

	public void setCotizacionProductoId(Integer cotizacionProductoId) {
		this.cotizacionProductoId = cotizacionProductoId;
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

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
