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

import com.pe.timy.entity.Compra;
import com.pe.timy.entity.CompraProducto;
import com.pe.timy.entity.Inventario;
import com.pe.timy.entity.Producto;
import com.pe.timy.entity.Proveedor;
import com.pe.timy.service.CompraProductoService;
import com.pe.timy.service.CompraService;
import com.pe.timy.service.EmpleadoService;
import com.pe.timy.service.InventarioService;
import com.pe.timy.service.ProductoService;
import com.pe.timy.service.ProveedorService;

@Controller
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	private CompraService compraService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private CompraProductoService compraProductoService;
	@Autowired
	private InventarioService inventarioService;
	

	List<CompraProducto> detalleCompra = new ArrayList<>();
	Compra compraGeneral = new Compra();

	@GetMapping(value = { "", "/" })
	public String listar(Model model) {
		model.addAttribute("compras", compraService.findAll());
		model.addAttribute("compra", new Compra());
		model.addAttribute("proveedores", proveedorService.findAllActive());
		return "admin/compra";
	}

	@PostMapping(value = "/generar")
	public String generar(Model model, Compra compra, Producto producto) {
		Optional<Proveedor> proveedor = proveedorService.findById(compra.getProveedor().getProveedorId());
		compra.setFecha(LocalDate.now());
		compraGeneral = compra;
		model.addAttribute("compra", compraGeneral);
		model.addAttribute("compraProducto", new CompraProducto());
		model.addAttribute("productos", productoService.findAllByProveedor(proveedor.get()));
		return "admin/compra_save";
	}

	@PostMapping(value = "/agregar-producto")
	public String agregarProducto(Model model, CompraProducto compraProducto) {
		/*Boolean repeticion = false;
		for (CompraProducto compra : detalleCompra) {
			if (compra.getProducto().equals(compraProducto.getProducto())) {
				repeticion = true;
				compraProducto.setCantidad(compraProducto.getCantidad()+compra.getCantidad());
				detalleCompra.remove(compra);
				System.out.print("TMR");
				detalleCompra.add(compraProducto);
			}
		}
		if (repeticion == false) {
			
		}*/
		detalleCompra.add(compraProducto);
		model.addAttribute("compra", compraGeneral);
		model.addAttribute("compraProducto", new CompraProducto());
		model.addAttribute("listaCompra", detalleCompra);
		model.addAttribute("productos", productoService.findAllByProveedor(compraGeneral.getProveedor()));
		model.addAttribute("total", obtenerMontoTotal());
		return "admin/compra_save";
	}
	
	@GetMapping(value = "/quitar-producto/{index}")
	public String quitarProducto(Model model, @PathVariable("index") int index) {
		detalleCompra.remove(index);
		model.addAttribute("compra", compraGeneral);
		model.addAttribute("compraProducto", new CompraProducto());
		model.addAttribute("listaCompra", detalleCompra);
		model.addAttribute("productos", productoService.findAllByProveedor(compraGeneral.getProveedor()));
		model.addAttribute("total", obtenerMontoTotal());
		return "admin/compra_save";
	}
	
	@GetMapping(value = "/aumentar-producto/{index}")
	public String aumentarProducto(Model model, @PathVariable("index") int index) {
		List<CompraProducto> nuevaListaCompraProductos = new ArrayList<>();
		for (CompraProducto compraProducto : detalleCompra) {
			if (compraProducto.equals(detalleCompra.get(index))) {
				compraProducto.setCantidad(compraProducto.getCantidad()+1);
			}
			nuevaListaCompraProductos.add(compraProducto);
		}
		detalleCompra = nuevaListaCompraProductos;
		model.addAttribute("compra", compraGeneral);
		model.addAttribute("compraProducto", new CompraProducto());
		model.addAttribute("listaCompra", detalleCompra);
		model.addAttribute("productos", productoService.findAllByProveedor(compraGeneral.getProveedor()));
		model.addAttribute("total", obtenerMontoTotal());
		return "admin/compra_save";
	}
	
	@GetMapping(value = "/disminuir-producto/{index}")
	public String disminuirProducto(Model model, @PathVariable("index") int index) {
		List<CompraProducto> nuevaListaCompraProductos = new ArrayList<>();
		for (CompraProducto compraProducto : detalleCompra) {
			if (compraProducto.equals(detalleCompra.get(index))) {
				int cantidad = compraProducto.getCantidad()-1;
				if (cantidad == 0) {
					return "redirect:/compra/quitar-producto/".concat(""+index);
				} else {
					compraProducto.setCantidad(cantidad);
				}
			}
			nuevaListaCompraProductos.add(compraProducto);
		}
		detalleCompra = nuevaListaCompraProductos;
		model.addAttribute("compra", compraGeneral);
		model.addAttribute("compraProducto", new CompraProducto());
		model.addAttribute("listaCompra", detalleCompra);
		model.addAttribute("productos", productoService.findAllByProveedor(compraGeneral.getProveedor()));
		model.addAttribute("total", obtenerMontoTotal());
		return "admin/compra_save";
	}

	@PostMapping(value = "/registrar")
	public String registrar(Compra compra, Authentication authentication) {
		compraGeneral.setEmpleado(empleadoService.findByUsuario(authentication.getName()));
		compraGeneral.setProveedor(compraGeneral.getProveedor());
		compraGeneral.setEstado("Pendiente");
		compraGeneral.setFecha(LocalDate.now());
		compraGeneral.setHora(LocalTime.now());

		for (CompraProducto detalle : detalleCompra) {
			detalle.setPrecioCompra(detalle.getProducto().getPrecioCompra());
		}
		compraGeneral.setMontoTotal(obtenerMontoTotal());
		compraService.save(compraGeneral);

		for (CompraProducto detalle : detalleCompra) {
			detalle.setCompra(compraGeneral);
			compraProductoService.save(detalle);
			Inventario inventario = inventarioService.findByProducto(detalle.getProducto()).get();
			inventario.setEntradas(inventario.getEntradas()+detalle.getCantidad());
			inventario.setStockActual(inventario.getEntradas()-inventario.getSalidas());
			inventarioService.save(inventario);
		}

		return "redirect:/compra/";
	}
	
	public Double obtenerMontoTotal() {
		Double montoTotal = 0.0;
		for (CompraProducto detalle : detalleCompra) {
			montoTotal = montoTotal + (detalle.getProducto().getPrecioCompra() * detalle.getCantidad());
		}
		return montoTotal;
	}
}
