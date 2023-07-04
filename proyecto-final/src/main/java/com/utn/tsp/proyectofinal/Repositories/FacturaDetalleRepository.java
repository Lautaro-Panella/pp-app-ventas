package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Long> {
}
