package com.pe.timy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer compraId;
	private LocalDate fecha;
	private LocalTime hora;
	private Double montoTotal;
	private String estado;
	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
	@ManyToOne
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
	@OneToMany(mappedBy = "compra")
	private List<CompraProducto> compraProductos = new ArrayList<>();
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCompraId() {
		return compraId;
	}

	public void setCompraId(Integer compraId) {
		this.compraId = compraId;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<CompraProducto> getCompraProductos() {
		return compraProductos;
	}

	public void setCompraProductos(List<CompraProducto> compraProductos) {
		this.compraProductos = compraProductos;
	}
}
