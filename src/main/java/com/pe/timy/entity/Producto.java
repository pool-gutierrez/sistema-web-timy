package com.pe.timy.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productoId;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String imagen;
	private Double precioCompra;
	private Double precioVenta;
	private Boolean estado;
}
