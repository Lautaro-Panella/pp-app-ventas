package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findAllByOrderByFacturaIdDesc();

    List<Factura> findFacturaByFechaBetween(Date fechaDesde, Date fechaHasta);

}
