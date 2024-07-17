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

import com.pe.timy.entity.Cotizacion;
import com.pe.timy.entity.CotizacionProducto;
import com.pe.timy.entity.Inventario;
import com.pe.timy.service.ClienteService;
import com.pe.timy.service.CotizacionProductoService;
import com.pe.timy.service.CotizacionService;
import com.pe.timy.service.EmpleadoService;
import com.pe.timy.service.InventarioService;
import com.pe.timy.service.ProductoService;

@Controller
@RequestMapping("/cotizacion")
public class CotizacionController {

	@Autowired
	private CotizacionService cotizacionService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private CotizacionProductoService cotizacionProductoService;
	@Autowired
	private InventarioService inventarioService;

	List<CotizacionProducto> detalleCotizacion = new ArrayList<>();
	Cotizacion cotizacionGeneral = new Cotizacion();
	Integer clienteIdGeneral = 0;

	@GetMapping(value = { "", "/" })
	public String listar(Model model) {
		model.addAttribute("cotizaciones", cotizacionService.findAll());
		model.addAttribute("cotizacion", new Cotizacion());
		return "admin/cotizacion";
	}

	@GetMapping(value = "/generar")
	public String generar(Model model, Cotizacion cotizacion) {
		cotizacion.setFecha(LocalDate.now());
		cotizacionGeneral = cotizacion;
		cotizacionGeneral.setCodigo(generarCodigo(cotizacionService.findAll().size() + 1));
		model.addAttribute("cotizacion", cotizacionGeneral);
		model.addAttribute("cotizacionProducto", new CotizacionProducto());
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/cotizacion_save";
	}

	@PostMapping(value = "/agregar-producto")
	public String agregarProducto(Model model, CotizacionProducto cotizacionProducto,
			@RequestParam(name = "clienteId", required = false) Integer clienteId) {
		Optional<Inventario> inventario = inventarioService.findByProducto(cotizacionProducto.getProducto());
		Integer stockActual = inventario.get().getStockActual();
		Integer stockMinimo = inventario.get().getStockMinimo();
		Boolean existencia = false;
		if (clienteId != null) {
			clienteIdGeneral = clienteId;
			model.addAttribute("clienteId", clienteId);
		}

		if (cotizacionProducto.getCantidad() < stockActual) {
			if ((stockActual - cotizacionProducto.getCantidad()) > stockMinimo) {
				for (CotizacionProducto detalle : detalleCotizacion) {
					if (detalle.getProducto().getProductoId()
							.equals(cotizacionProducto.getProducto().getProductoId())) {
						Integer nuevaCantidad = detalle.getCantidad() + cotizacionProducto.getCantidad();
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
					detalleCotizacion.add(cotizacionProducto);
				}
				model.addAttribute("total", obtenerMontoTotal());
			} else {
				model.addAttribute("errorsm", true);
			}
		} else {
			model.addAttribute("errorsa", true);
		}
		model.addAttribute("listaCotizacion", detalleCotizacion);
		model.addAttribute("cotizacion", cotizacionGeneral);
		model.addAttribute("cotizacionProducto", new CotizacionProducto());
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("total", obtenerMontoTotal());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/cotizacion_save";
	}

	@GetMapping(value = "/quitar-producto/{index}")
	public String quitarProducto(Model model, @PathVariable("index") int index) {
		detalleCotizacion.remove(index);
		if (clienteIdGeneral != null) {
			model.addAttribute("clienteId", clienteIdGeneral);
		}
		model.addAttribute("cotizacion", cotizacionGeneral);
		model.addAttribute("cotizacionProducto", new CotizacionProducto());
		model.addAttribute("listaCotizacion", detalleCotizacion);
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("total", obtenerMontoTotal());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/cotizacion_save";
	}

	@GetMapping(value = "/aumentar-producto/{index}")
	public String aumentarProducto(Model model, @PathVariable("index") int index) {
		CotizacionProducto detalle = detalleCotizacion.get(index);
		Optional<Inventario> inventario = inventarioService.findByProducto(detalle.getProducto());
		Integer stockActual = inventario.get().getStockActual();
		Integer stockMinimo = inventario.get().getStockMinimo();
		Integer nuevaCantidad = detalle.getCantidad() + 1;
		if (clienteIdGeneral != null) {
			model.addAttribute("clienteId", clienteIdGeneral);
		}
		List<CotizacionProducto> nuevaListaCotizacionProductos = new ArrayList<>();
		
		if ((stockActual - nuevaCantidad) > stockMinimo) {
			for (CotizacionProducto cotizacionProducto : detalleCotizacion) {
				if (cotizacionProducto.equals(detalleCotizacion.get(index))) {
					cotizacionProducto.setCantidad(nuevaCantidad);
				}
				nuevaListaCotizacionProductos.add(cotizacionProducto);
				detalleCotizacion = nuevaListaCotizacionProductos;
			}
		} else {
			model.addAttribute("errorsm", true);
		}
		
		model.addAttribute("cotizacion", cotizacionGeneral);
		model.addAttribute("cotizacionProducto", new CotizacionProducto());
		model.addAttribute("listaCotizacion", detalleCotizacion);
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("total", obtenerMontoTotal());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/cotizacion_save";
	}

	@GetMapping(value = "/disminuir-producto/{index}")
	public String disminuirProducto(Model model, @PathVariable("index") int index) {
		List<CotizacionProducto> nuevaListaCotizacionProductos = new ArrayList<>();
		for (CotizacionProducto cotizacionProducto : detalleCotizacion) {
			if (cotizacionProducto.equals(detalleCotizacion.get(index))) {
				int cantidad = cotizacionProducto.getCantidad() - 1;
				if (cantidad == 0) {
					return "redirect:/cotizacion/quitar-producto/".concat("" + index);
				} else {
					cotizacionProducto.setCantidad(cantidad);
				}
			}
			nuevaListaCotizacionProductos.add(cotizacionProducto);
		}
		detalleCotizacion = nuevaListaCotizacionProductos;
		if (clienteIdGeneral != null) {
			model.addAttribute("clienteId", clienteIdGeneral);
		}
		model.addAttribute("cotizacion", cotizacionGeneral);
		model.addAttribute("cotizacionProducto", new CotizacionProducto());
		model.addAttribute("listaCotizacion", detalleCotizacion);
		model.addAttribute("productos", productoService.findAllActive());
		model.addAttribute("total", obtenerMontoTotal());
		model.addAttribute("clientes", clienteService.findAllActive());
		return "admin/cotizacion_save";
	}

	@PostMapping(value = "/registrar")
	public String registrar(Cotizacion cotizacion, Authentication authentication) {
		cotizacionGeneral.setEmpleado(empleadoService.findByUsuario(authentication.getName()));
		cotizacionGeneral.setCliente(clienteService.findById(clienteIdGeneral).get());
		cotizacionGeneral.setEstado("COTIZADO");
		cotizacionGeneral.setFecha(LocalDate.now());
		cotizacionGeneral.setHora(LocalTime.now());

		for (CotizacionProducto detalle : detalleCotizacion) {
			detalle.setPrecioVenta(detalle.getProducto().getPrecioVenta());
		}
		cotizacionGeneral.setDescuento(cotizacion.getDescuento());
		cotizacionGeneral.setMontoTotal(cotizacion.getMontoTotal());
		cotizacionService.save(cotizacionGeneral);

		for (CotizacionProducto detalle : detalleCotizacion) {
			detalle.setCotizacion(cotizacionGeneral);
			cotizacionProductoService.save(detalle);
		}

		detalleCotizacion.clear();
		cotizacionGeneral = null;
		clienteIdGeneral = null;
		return "redirect:/cotizacion/";
	}

	public Double obtenerMontoTotal() {
		Double montoTotal = 0.0;
		for (CotizacionProducto detalle : detalleCotizacion) {
			montoTotal = montoTotal + (detalle.getProducto().getPrecioVenta() * detalle.getCantidad());
		}
		return Math.round(montoTotal * 100d) / 100d;
	}

	public String generarCodigo(int numero) {
		String codigo = String.format("0001-%06d", numero);
		return codigo;
	}
}
