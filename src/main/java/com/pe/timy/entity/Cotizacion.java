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
@Table(name = "cotizacion")
public class Cotizacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cotizacionId;
	private String codigo;
	private LocalDate fecha;
	private LocalTime hora;
	private Double descuento;
	private Double montoTotal;
	//Estado = {Emitido, Vendido}
	private String estado;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
	@OneToMany(mappedBy = "cotizacion")
	private List<CotizacionProducto> cotizacionProductos = new ArrayList<>();
	
	public Cotizacion() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCotizacionId() {
		return cotizacionId;
	}

	public void setCotizacionId(Integer cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<CotizacionProducto> getCotizacionProductos() {
		return cotizacionProductos;
	}

	public void setCotizacionProductos(List<CotizacionProducto> cotizacionProductos) {
		this.cotizacionProductos = cotizacionProductos;
	}
}
