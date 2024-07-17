package com.pe.timy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pe.timy.entity.CompraProducto;

public interface CompraProductoRepository extends JpaRepository<CompraProducto, Integer> {

	@Query("SELECT cp.compra.fecha, SUM(cp.cantidad) FROM CompraProducto cp "
			+ "WHERE cp.producto.productoId = :productoId GROUP BY cp.compra.fecha")
	List<Object[]> findTotalProductosVendidosPorDia(@Param("productoId") Integer productoId);
}
