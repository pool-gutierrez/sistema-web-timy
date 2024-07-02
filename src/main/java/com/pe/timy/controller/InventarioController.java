package com.pe.timy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pe.timy.entity.Inventario;
import com.pe.timy.service.InventarioService;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

	@Autowired
	private InventarioService inventarioService;
	
	@GetMapping(value = { "", "/" })
	public String listar(Model model) {
		model.addAttribute("inventarios", inventarioService.findByProductoEstadoTrue());
		return "admin/inventario";
	}
	
	@PostMapping(value = "/actualizar/{inventarioId}")
	public String actualizar(@PathVariable(name = "inventarioId") Integer inventarioId, Inventario inventario) {
		Optional<Inventario> inventarioDb = inventarioService.findById(inventarioId);
		inventarioDb.get().setStockMinimo(inventario.getStockMinimo());
		inventarioService.save(inventarioDb.get());
		return "redirect:/inventario";
	}
}
