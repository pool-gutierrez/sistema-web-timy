package com.pe.timy.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pe.timy.entity.Inventario;
import com.pe.timy.entity.Venta;
import com.pe.timy.entity.VentaProducto;
import com.pe.timy.service.ClienteService;
import com.pe.timy.service.EmpleadoService;
import com.pe.timy.service.InventarioService;
import com.pe.timy.service.ProductoService;
import com.pe.timy.service.VentaProductoService;
import com.pe.timy.service.VentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private VentaProductoService ventaProductoService;
	@Autowired
	private InventarioService inventarioService;

	List<VentaProducto> detalleVenta = new ArrayList<>();
	Venta ventaGeneral = new Venta();
	Integer clienteIdGeneral = 0;

	@GetMapping(value = { "", "/" })
	public String listar(Model model) {
		model.addAttribute("ventas", ventaService.findAll());
		model.addAttribute("venta", new Venta());
		return "admin/venta";
	}

	@GetMapping(value = "/generar")
	public String generar(Model model, Venta venta) {
		venta.setFecha(LocalDate.now());
		ventaGeneral = venta;
		model.addAttribute("venta", ventaGeneral);
		model.addAttribute("ventaProducto", new VentaProducto());
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/venta_save";
	}

	@PostMapping(value = "/agregar-producto")
	public String agregarProducto(Model model, VentaProducto ventaProducto,
			@RequestParam(name = "clienteId", required = false) Integer clienteId) {
		Optional<Inventario> inventario = inventarioService.findByProducto(ventaProducto.getProducto());
		Integer stockActual = inventario.get().getStockActual();
		Integer stockMinimo = inventario.get().getStockMinimo();
		Boolean existencia = false;

		model.addAttribute("venta", ventaGeneral);
		model.addAttribute("ventaProducto", new VentaProducto());
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("clientes", clienteService.findAllActive());
		if (clienteId != null) {
			clienteIdGeneral = clienteId;
			model.addAttribute("clienteId", clienteId);
		}
		if (ventaProducto.getCantidad() < stockActual) {
			if ((stockActual - ventaProducto.getCantidad()) > stockMinimo) {
				for (VentaProducto detalle : detalleVenta) {
					if (detalle.getProducto().getProductoId().equals(ventaProducto.getProducto().getProductoId())) {
						Integer nuevaCantidad = detalle.getCantidad() + ventaProducto.getCantidad();
						if ((stockActual - nuevaCantidad) > stockMinimo) {
							detalle.setCantidad(nuevaCantidad);
						} else {
							model.addAttribute("errorsm", true);
						}
						existencia = true;
						break;
					}
				}
				if (existencia == false) {
					detalleVenta.add(ventaProducto);
				}
				model.addAttribute("total", obtenerMontoTotal());
			} else {
				model.addAttribute("errorsm", true);
			}
		} else {
			model.addAttribute("errorsa", true);
		}
		model.addAttribute("listaVenta", detalleVenta);
		return "admin/venta_save";
	}

	@GetMapping(value = "/quitar-producto/{index}")
	public String quitarProducto(Model model, @PathVariable("index") int index) {
		detalleVenta.remove(index);
		if (clienteIdGeneral != null) {
			model.addAttribute("clienteId", clienteIdGeneral);
		}
		model.addAttribute("venta", ventaGeneral);
		model.addAttribute("ventaProducto", new VentaProducto());
		model.addAttribute("listaVenta", detalleVenta);
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("total", obtenerMontoTotal());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/venta_save";
	}

	@GetMapping(value = "/aumentar-producto/{index}")
	public String aumentarProducto(Model model, @PathVariable("index") int index) {
		VentaProducto detalle = detalleVenta.get(index);
		Optional<Inventario> inventario = inventarioService.findByProducto(detalle.getProducto());
		Integer stockActual = inventario.get().getStockActual();
		Integer stockMinimo = inventario.get().getStockMinimo();
		Integer nuevaCantidad = detalle.getCantidad() + 1;
		if (clienteIdGeneral != null) {
			model.addAttribute("clienteId", clienteIdGeneral);
		}
		model.addAttribute("venta", ventaGeneral);
		model.addAttribute("ventaProducto", new VentaProducto());
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("clientes", clienteService.findAllActive());
		List<VentaProducto> nuevaListaVentaProductos = new ArrayList<>();

		if ((stockActual - nuevaCantidad) > stockMinimo) {
			for (VentaProducto ventaProducto : detalleVenta) {
				if (ventaProducto.equals(detalleVenta.get(index))) {
					ventaProducto.setCantidad(nuevaCantidad);
				}
				nuevaListaVentaProductos.add(ventaProducto);
				detalleVenta = nuevaListaVentaProductos;
			}
		} else {
			model.addAttribute("errorsm", true);
		}

		model.addAttribute("listaVenta", detalleVenta);
		model.addAttribute("total", obtenerMontoTotal());
		return "admin/venta_save";
	}

	@GetMapping(value = "/disminuir-producto/{index}")
	public String disminuirProducto(Model model, @PathVariable("index") int index) {
		List<VentaProducto> nuevaListaVentaProductos = new ArrayList<>();
		for (VentaProducto ventaProducto : detalleVenta) {
			if (ventaProducto.equals(detalleVenta.get(index))) {
				int cantidad = ventaProducto.getCantidad() - 1;
				if (cantidad == 0) {
					return "redirect:/venta/quitar-producto/".concat("" + index);
				} else {
					ventaProducto.setCantidad(cantidad);
				}
			}
			nuevaListaVentaProductos.add(ventaProducto);
		}
		detalleVenta = nuevaListaVentaProductos;
		if (clienteIdGeneral != null) {
			model.addAttribute("clienteId", clienteIdGeneral);
		}
		model.addAttribute("venta", ventaGeneral);
		model.addAttribute("ventaProducto", new VentaProducto());
		model.addAttribute("listaVenta", detalleVenta);
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("total", obtenerMontoTotal());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/venta_save";
	}

	@PostMapping(value = "/registrar")
	public String registrar(Venta venta, Authentication authentication) {
		ventaGeneral.setEmpleado(empleadoService.findByUsuario(authentication.getName()));
		ventaGeneral.setCliente(clienteService.findById(clienteIdGeneral).get());
		ventaGeneral.setEstado("Pendiente");
		ventaGeneral.setFecha(LocalDate.now());
		ventaGeneral.setHora(LocalTime.now());

		for (VentaProducto detalle : detalleVenta) {
			detalle.setPrecioVenta(detalle.getProducto().getPrecioVenta());
		}
		ventaGeneral.setDescuento(venta.getDescuento());
		ventaGeneral.setMontoTotal(venta.getMontoTotal());
		ventaService.save(ventaGeneral);

		for (VentaProducto detalle : detalleVenta) {
			detalle.setVenta(ventaGeneral);
			ventaProductoService.save(detalle);
			Inventario inventario = inventarioService.findByProducto(detalle.getProducto()).get();
			inventario.setSalidas(inventario.getSalidas() + detalle.getCantidad());
			inventario.setStockActual(inventario.getEntradas() - inventario.getSalidas());
			inventarioService.save(inventario);
		}

		detalleVenta.clear();
		ventaGeneral = null;
		clienteIdGeneral = null;
		return "redirect:/venta/";
	}

	public Double obtenerMontoTotal() {
		Double montoTotal = 0.0;
		for (VentaProducto detalle : detalleVenta) {
			montoTotal = montoTotal + (detalle.getProducto().getPrecioVenta() * detalle.getCantidad());
		}
		return Math.round(montoTotal * 100d) / 100d;
	}
}
