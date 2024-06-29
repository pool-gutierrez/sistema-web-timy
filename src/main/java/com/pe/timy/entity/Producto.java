package com.pe.timy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productoId;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String imagen;
	private Double precioCompra;
	private Double precioVenta;
	private Boolean estado;
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	@OneToMany(mappedBy = "producto")
	private List<VentaProducto> ventaProductos = new ArrayList<>();
	@OneToMany(mappedBy = "producto")
	private List<CompraProducto> compraProductos = new ArrayList<>();
	@OneToMany(mappedBy = "producto")
	private List<CompraProducto> cotizacionProductos = new ArrayList<>();
	@OneToMany(mappedBy = "producto")
	private List<Inventario> inventarios = new ArrayList<>();
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<VentaProducto> getVentaProductos() {
		return ventaProductos;
	}

	public void setVentaProductos(List<VentaProducto> ventaProductos) {
		this.ventaProductos = ventaProductos;
	}

	public List<CompraProducto> getCompraProductos() {
		return compraProductos;
	}

	public void setCompraProductos(List<CompraProducto> compraProductos) {
		this.compraProductos = compraProductos;
	}

	public List<CompraProducto> getCotizacionProductos() {
		return cotizacionProductos;
	}

	public void setCotizacionProductos(List<CompraProducto> cotizacionProductos) {
		this.cotizacionProductos = cotizacionProductos;
	}

	public List<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}
}
