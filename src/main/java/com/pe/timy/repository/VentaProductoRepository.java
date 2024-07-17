package com.pe.timy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pe.timy.entity.VentaProducto;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, Integer> {

	@Query("SELECT vp.venta.fecha, SUM(vp.cantidad) FROM VentaProducto vp WHERE vp.producto.productoId = :productoId "
			+ "GROUP BY vp.venta.fecha")
	List<Object[]> findTotalProductosVendidosPorDia(@Param("productoId") Integer productoId);
}
