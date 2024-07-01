package com.pe.timy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pe.timy.entity.Cliente;
import com.pe.timy.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping({ "/", "" })
	public String listar(Model model) {
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("cliente", new Cliente());
		return "admin/cliente";
	}

	@PostMapping("/registrar")
	public String registrar(Cliente cliente) {
		cliente.setNombres(cliente.getNombres().trim());
		cliente.setApellidos(cliente.getApellidos().trim());
		cliente.setCorreo(cliente.getCorreo().trim());
		cliente.setEstado(true);
		clienteService.save(cliente);
		return "redirect:/cliente";
	}

	@PostMapping("/actualizar/{clienteId}")
	public String actualizar(@PathVariable("clienteId") Integer clienteId, Cliente cliente) {
		Optional<Cliente> clienteDb = clienteService.findById(clienteId);
		cliente.setNombres(cliente.getNombres().trim());
		cliente.setApellidos(cliente.getApellidos().trim());
		cliente.setCorreo(cliente.getCorreo().trim());
		cliente.setEstado(clienteDb.get().getEstado());
		clienteService.save(cliente);
		return "redirect:/cliente";
	}

	@GetMapping("/cambiar-estado/{clienteId}")
	public String getMethodName(@PathVariable("clienteId") Integer clienteId) {
		Optional<Cliente> clienteDb = clienteService.findById(clienteId);
		if (clienteDb.get().getEstado() == true) {
			clienteDb.get().setEstado(false);
		} else {
			clienteDb.get().setEstado(true);
		}
		clienteService.save(clienteDb.get());
		return "redirect:/cliente";
	}
}
