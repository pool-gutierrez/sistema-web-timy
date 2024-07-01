package com.pe.timy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pe.timy.entity.Empleado;
import com.pe.timy.service.EmpleadoService;
import com.pe.timy.service.GeneralService;
import com.pe.timy.service.RolService;

@Controller
@RequestMapping("empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private RolService rolService;
	@Autowired
	private GeneralService generalService;

	@GetMapping({ "/", "" })
	public String listar(Model model) {
		model.addAttribute("empleados", empleadoService.findAll());
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("roles", rolService.findAll());
		return "admin/empleado";
	}

	@PostMapping("/registrar")
	public String registrar(Empleado empleado) {
		empleado.setNombres(empleado.getNombres().trim());
		empleado.setApellidos(empleado.getApellidos().trim());
		empleado.setCorreo(empleado.getCorreo().trim());
		empleado.setUsuario(empleado.getUsuario().trim());
		empleado.setContrasena(generalService.encryptPassword(empleado.getContrasena()));
		empleado.setEstado(true);
		empleadoService.save(empleado);
		return "redirect:/empleado";
	}

	@PostMapping(value = "/actualizar/{empleadoId}")
	public String actualizar(@PathVariable(name = "empleadoId") Integer empleadoId, Empleado empleado,
			@RequestParam(name = "newPassword", required = false) String newPassword, Model model) {
		Optional<Empleado> empleadoDb = empleadoService.findById(empleadoId);
		if (empleado.getContrasena().equals("")) {
			empleado.setContrasena(empleadoDb.get().getContrasena());
			empleado.setEstado(empleadoDb.get().getEstado());
			empleado.setUsuario(empleado.getUsuario().trim());
			empleadoService.save(empleado);
			return "redirect:/empleado";
		} else {
			if (empleado.getContrasena().equals(newPassword)) {
				empleado.setContrasena(generalService.encryptPassword(empleado.getContrasena()));
				empleado.setEstado(empleadoDb.get().getEstado());
				empleado.setUsuario(empleado.getUsuario().trim());
				empleadoService.save(empleado);
				return "redirect:/empleado";
			} else {
				return "redirect:/empleado";
			}
		}
	}

	@GetMapping("/cambiar-estado/{empleadoId}")
	public String cambiarEstado(@PathVariable("empleadoId") Integer empleadoId) {
		Optional<Empleado> empleadoDb = empleadoService.findById(empleadoId);
		if (empleadoDb.get().getEstado() == true) {
			empleadoDb.get().setEstado(false);
		} else {
			empleadoDb.get().setEstado(true);
		}
		empleadoService.save(empleadoDb.get());
		return "redirect:/empleado";
	}
}
