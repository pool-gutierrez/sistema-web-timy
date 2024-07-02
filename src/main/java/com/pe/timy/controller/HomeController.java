package com.pe.timy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pe.timy.entity.Producto;
import com.pe.timy.service.ProductoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	@Autowired
	private ProductoService productoService;

	@GetMapping(value = "/login")
	public String login(Authentication authentication) {
		if (authentication != null) {
			return "redirect:/producto";
		}
		return "admin/login";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}

	@GetMapping(value = { "", "/" })
	public String mostrarCatalogo(Model model) {
		model.addAttribute("otrosProductos", productoService.findAllActive());
		model.addAttribute("productos", productoService.findAllActive());
		return "web/index";
	}

	@GetMapping("/catalogo/{productoId}")
	public String verDetalle(@PathVariable("productoId") Integer productoId, Model model,
			Authentication authentication) {
		Producto producto = productoService.findById(productoId).get();
		if (producto.getEstado().booleanValue() == false) {
			return "redirect:/";
		} else {
			List<Producto> productosRelacionados = productoService.findAllByCategoria(producto.getCategoria());
			for (int i = 0; i < productosRelacionados.size(); i++) {
				productosRelacionados.remove(producto);
			}
			model.addAttribute("producto", producto);
			model.addAttribute("productosRelacionados", productosRelacionados);
			return "web/producto_detail";
		}
	}
}
