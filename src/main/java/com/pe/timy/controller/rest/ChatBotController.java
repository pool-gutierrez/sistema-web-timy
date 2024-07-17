package com.pe.timy.controller.rest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pe.timy.service.GeneralService;
import com.pe.timy.util.Chatbot;
import com.pe.timy.util.ChatbotService;
import com.pe.timy.util.DefaultMessages;
import com.pe.timy.util.MensajeMapper;
import com.pe.timy.util.Response;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SessionScope
public class ChatBotController {

	@Autowired
	private ChatbotService chatBotService;
	@Autowired
	private GeneralService generalService;
	private List<Chatbot> mensajes = new ArrayList<>();

	@GetMapping(value = { "", "/" })
	public String getMethodName(Model model) {
		if (mensajes.size() == 0) {
			mensajes.add(new Chatbot(1, "asistente", DefaultMessages.GREETING_MESSAGE, LocalTime.now()));
		}
		model.addAttribute("mensajes", mensajes);
		return "web/chatbot";
	}

	@GetMapping("/enviar")
	public String postMethodName(@RequestParam String mensaje) throws JsonMappingException, JsonProcessingException {
		Gson gson = new Gson();
		if (mensajes.size() == 0) {
			mensajes.add(new Chatbot(1, "asistente", DefaultMessages.GREETING_MESSAGE, LocalTime.now()));
		} else {
			if (mensaje != "") {
				String inventario = gson.toJson(generalService.mapperList());
				List<MensajeMapper> mensajesMapper = new ArrayList<>();
				for (Chatbot chatbot : mensajes) {
					if (chatbot.getChatbotId() % 2 == 0) {
						mensajesMapper.add(new MensajeMapper(chatbot.getChatbotId(), "Cliente", chatbot.getMensaje()));
					} else {
						mensajesMapper.add(new MensajeMapper(chatbot.getChatbotId(), "Tú", chatbot.getMensaje()));
					}
				}
				String mensajesCliente = gson.toJson(mensajesMapper);
				String consulta = "Hola, me han enviado la siguiente consulta:\n\"" + mensaje + "\"\n"
						+ "Para responder ello, puedes evaluar mi inventario, ya sea por el nombre de producto o por las categorías:\n"
						+ inventario + "\n"
						+ "Además, aquí tienes las consultas prevías que se han hecho (Las que dicen 'tú', las hiciste tú, las otras el cliente): \n\""
						+ mensajesCliente + "\"\n" + "Si el cliente ya ha saludo, no es necesario saludar de nuevo.\n"
						+ "Finalmente, si la consulta está relacionada con mis productos o categorías, genera una respuesta adecuada y amigable para el cliente, caso contrario también.\n"
						+ "Mi empresa es Timy E.I.R.L. Muchas gracias. La respuesta no debe ser muy extensa.";

				String respuesta = chatBotService.callApi(consulta);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				Response response = objectMapper.readValue(respuesta, Response.class);
				mensajes.add(new Chatbot(mensajes.size() + 1,"cliente",  mensaje, LocalTime.now()));
				mensajes.add(new Chatbot(mensajes.size() + 1, "asistente", 
						response.getCandidates().get(0).getContent().getParts().get(0).getText(), LocalTime.now()));
			}
		}
		return gson.toJson(mensajes);
	}

}
