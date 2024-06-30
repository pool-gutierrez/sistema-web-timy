package com.pe.timy.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pe.timy.entity.Producto;
import com.pe.timy.service.CategoriaService;
import com.pe.timy.service.CloudinaryService;
import com.pe.timy.service.ProductoService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CloudinaryService cloudinaryService;

	@GetMapping({ "/", "" })
	public String listar(Model model) {
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("categorias", categoriaService.findAllActive());
		model.addAttribute("producto", new Producto());
		return "admin/producto";
	}

	@PostMapping(value = "/registrar", consumes = "multipart/form-data")
	public String registrar(Producto producto, @RequestParam("img") MultipartFile imagen) throws IOException {
		Map<?, ?> result = cloudinaryService.upload(imagen);
		producto.setEstado(true);
		producto.setNombre(producto.getNombre().trim());
		producto.setDescripcion(producto.getDescripcion().trim());
		producto.setCodigo(producto.getCodigo().trim());
		producto.setImagen(result.get("secure_url").toString());
		producto.setImagenId(result.get("public_id").toString());
		productoService.save(producto);
		return "redirect:/producto/";
	}

	@PostMapping("/actualizar/{productoId}")
	public String actualizar(@PathVariable("productoId") Integer productoId, Producto producto,
			@RequestParam("img") MultipartFile imagen) throws IOException {
		Optional<Producto> productoDb = productoService.findById(productoId);
		if (imagen.getBytes().length == 0) {
			producto.setEstado(productoDb.get().getEstado());
			producto.setNombre(producto.getNombre().trim());
			producto.setDescripcion(producto.getDescripcion().trim());
			producto.setCodigo(producto.getCodigo().trim());
			producto.setImagen(productoDb.get().getImagen());
			producto.setImagenId(productoDb.get().getImagenId());
			productoService.save(producto);
			return "redirect:/producto/";
		}
		cloudinaryService.delete(productoDb.get().getImagenId());
		Map<?, ?> result = cloudinaryService.upload(imagen);
		producto.setEstado(productoDb.get().getEstado());
		producto.setNombre(producto.getNombre().trim());
		producto.setDescripcion(producto.getDescripcion().trim());
		producto.setCodigo(producto.getCodigo().trim());
		producto.setImagen(result.get("secure_url").toString());
		producto.setImagenId(result.get("public_id").toString());
		productoService.save(producto);
		return "redirect:/producto/";
	}

	@GetMapping(value = "/cambiar-estado/{productoId}")
	public String cambiarEstado(@PathVariable("productoId") Integer productoId) {
		Optional<Producto> productoDb = productoService.findById(productoId);
		if (productoDb.get().getEstado() == true) {
			productoDb.get().setEstado(false);
		} else {
			productoDb.get().setEstado(true);
		}
		productoService.save(productoDb.get());
		return "redirect:/producto";
	}
}
