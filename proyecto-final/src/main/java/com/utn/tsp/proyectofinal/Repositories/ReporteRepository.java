package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.DTO.HistoricoVentasDTO;
import com.utn.tsp.proyectofinal.Model.DTO.RankingProductosDTO;
import com.utn.tsp.proyectofinal.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT SUM(R0.subtotal * ((100 - R0.porcentaje_descuento) / 100)) AS ganancia\n" +
            "FROM factura R0\n" +
            "WHERE (R0.fecha >= :fechaDesde AND R0.fecha <= :fechaHasta);", nativeQuery = true)
    Double getGanancia(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);

    @Query(value = "SELECT R1.descripcion AS descripcion, R1.talle, SUM(R0.cantidad) AS cantidad \n" +
            "FROM factura_detalle R0\n" +
            "INNER JOIN producto R1 ON R1.producto_id = R0.producto_id\n" +
            "GROUP BY R1.producto_id\n" +
            "ORDER BY cantidad DESC;", nativeQuery = true)
    List<RankingProductosDTO> getProductosMasVendidos();

    @Query(value = "SELECT R1.descripcion AS descripcion, R1.talle, SUM(R0.cantidad) AS cantidad \n" +
            "FROM factura_detalle R0\n" +
            "INNER JOIN producto R1 ON R1.producto_id = R0.producto_id\n" +
            "GROUP BY R1.producto_id\n" +
            "ORDER BY cantidad ASC;", nativeQuery = true)
    List<RankingProductosDTO> getProductosMenosVendidos();

    @Query(value = "SELECT R1.factura_id AS facturaId, R1.fecha, R1.observaciones, R1.subtotal, R1.porcentaje_descuento AS porcentajeDescuento, CONCAT(R3.nombre, ' ', R3.apellido) AS cliente, R0.factura_detalle_id AS facturaDetalleId, R2.descripcion AS producto, R2.talle, R0.cantidad, R0.precio_unitario AS precioUnitario\n" +
            "FROM factura_detalle R0\n" +
            "INNER JOIN factura R1 ON R1.factura_id =  R0.factura_id\n" +
            "INNER JOIN producto R2 ON R2.producto_id = R0.producto_id\n" +
            "INNER JOIN cliente R3 ON R3.cliente_id = R1.cliente_id;", nativeQuery = true)
    List<HistoricoVentasDTO> getHistoricoVentas();

}
