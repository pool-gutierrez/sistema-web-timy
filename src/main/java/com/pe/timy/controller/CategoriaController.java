package com.pe.timy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pe.timy.entity.Categoria;
import com.pe.timy.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping({"/", ""})
	public String listar(Model model) {
		model.addAttribute("categorias", categoriaService.findAll());
		model.addAttribute("categoria", new Categoria());
		return "admin/categoria";
	}
	
	@PostMapping("/registrar")
	public String registrar(Categoria categoria) {
		categoria.setEstado(true);
		categoria.setNombre(categoria.getNombre().trim());
		categoriaService.save(categoria);
		return "redirect:/categoria/";
	}
	
	@PostMapping(value = "/actualizar/{categoriaId}")
	public String actualizar(@PathVariable(name = "categoriaId") Integer categoriaId, Categoria categoria) {
		Optional<Categoria> categoriaDb = categoriaService.findById(categoriaId);
		categoria.setEstado(categoriaDb.get().getEstado());
		categoria.setNombre(categoria.getNombre().trim());
		categoriaService.save(categoria);
		return "redirect:/categoria";
	}
	
	@GetMapping(value = "/cambiar-estado/{categoriaId}")
	public String cambiarEstado(@PathVariable(name = "categoriaId") Integer categoriaId) {
		Optional<Categoria> categoriaDb = categoriaService.findById(categoriaId);
		if (categoriaDb.get().getEstado() == true) {
			categoriaDb.get().setEstado(false);
		} else {
			categoriaDb.get().setEstado(true);
		}
		categoriaService.save(categoriaDb.get());
		return "redirect:/categoria";
	}
}
