package com.pe.timy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pe.timy.entity.Proveedor;
import com.pe.timy.service.ProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@GetMapping({ "/", "" })
	public String listar(Model model) {
		model.addAttribute("proveedores", proveedorService.findAll());
		model.addAttribute("proveedor", new Proveedor());
		return "admin/proveedor";
	}

	@PostMapping("/registrar")
	public String registrar(Proveedor proveedor) {
		proveedor.setNombre(proveedor.getNombre().trim());
		proveedor.setCorreo(proveedor.getCorreo().trim());
		proveedor.setDireccion(proveedor.getDireccion().trim());
		proveedor.setEstado(true);
		proveedorService.save(proveedor);
		return "redirect:/proveedor";
	}

	@PostMapping("/actualizar/{proveedorId}")
	public String actualizar(@PathVariable("proveedorId") Integer proveedorId, Proveedor proveedor) {
		Optional<Proveedor> proveedorDb = proveedorService.findById(proveedorId);
		proveedor.setNombre(proveedor.getNombre().trim());
		proveedor.setCorreo(proveedor.getCorreo().trim());
		proveedor.setDireccion(proveedor.getDireccion().trim());
		proveedor.setEstado(proveedorDb.get().getEstado());
		proveedorService.save(proveedor);
		return "redirect:/proveedor";
	}

	@GetMapping("/cambiar-estado/{proveedorId}")
	public String cambiarEstado(@PathVariable("proveedorId") Integer proveedorId) {
		Optional<Proveedor> proveedorDb = proveedorService.findById(proveedorId);
		if (proveedorDb.get().getEstado() == true) {
			proveedorDb.get().setEstado(false);
		} else {
			proveedorDb.get().setEstado(true);
		}
		proveedorService.save(proveedorDb.get());
		return "redirect:/proveedor";
	}
}
