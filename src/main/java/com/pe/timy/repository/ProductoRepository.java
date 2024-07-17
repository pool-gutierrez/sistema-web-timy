package com.pe.timy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pe.timy.entity.Categoria;
import com.pe.timy.entity.Producto;
import com.pe.timy.entity.Proveedor;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	List<Producto> findAllByEstado(Boolean estado);
	List<Producto> findAllByProveedorAndEstado(Proveedor proveedor, Boolean estado);
	List<Producto> findAllByCategoriaAndEstado(Categoria categoria, Boolean estado);
	@Query("SELECT DISTINCT p FROM Producto p JOIN p.inventarios inv WHERE inv.stockActual > inv.stockMinimo")
    List<Producto> findProductosConStockSuperiorAlMinimo();
}
