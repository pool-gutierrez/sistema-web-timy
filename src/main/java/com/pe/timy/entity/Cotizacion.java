package com.pe.timy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
