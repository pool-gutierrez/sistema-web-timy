package com.pe.timy.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Chatbot {

	private Integer chatbotId;
	private String mensaje;
	private String hora;

	public Chatbot() {
	}

	public Chatbot(Integer chatbotId, String mensaje, LocalTime hora) {
		this.chatbotId = chatbotId;
		this.mensaje = mensaje;
		this.hora = formatHora(hora);
	}

	public Integer getChatbotId() {
		return chatbotId;
	}

	public void setChatbotId(Integer chatbotId) {
		this.chatbotId = chatbotId;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	private String formatHora(LocalTime localTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
		String formattedTime = localTime.format(formatter);
		return formattedTime;
	}
}
