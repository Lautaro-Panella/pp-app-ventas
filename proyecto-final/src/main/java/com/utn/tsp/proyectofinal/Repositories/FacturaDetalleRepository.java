package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Long> {

    List<FacturaDetalle> findFacturaDetalleByFactura_FacturaId(Long facturaId);

}
