package com.pe.timy.util;

public class MensajeMapper {

	private Integer id;
	private String emisor;
	private String mensaje;

	public MensajeMapper(Integer id, String emisor, String mensaje) {
		this.id = id;
		this.emisor = emisor;
		this.mensaje = mensaje;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
