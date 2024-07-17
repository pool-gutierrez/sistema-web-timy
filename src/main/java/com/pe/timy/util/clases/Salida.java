package com.pe.timy.util.clases;

import java.time.LocalDate;

public class Salida {

	private String fecha;
	private Integer cantidad;

	public Salida() {
	}

	public Salida(LocalDate fecha, Integer cantidad) {
		this.fecha = fecha.toString();
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
