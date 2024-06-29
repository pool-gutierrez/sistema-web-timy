package com.pe.timy.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empleadoId;
	private String nombres;
	private String apellidos;
	private Integer dni;
	private Integer celular;
	private String correo;
	private String usuario;
	private String contrasena;
	private Boolean estado;

}
