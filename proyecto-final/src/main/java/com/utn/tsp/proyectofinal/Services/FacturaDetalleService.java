package com.utn.tsp.proyectofinal.Services;

import com.utn.tsp.proyectofinal.Model.FacturaDetalle;
import com.utn.tsp.proyectofinal.Repositories.FacturaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaDetalleService {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    /**
     *
     * @return
     */
    public List<FacturaDetalle> getFacturaDetalles() {
        return facturaDetalleRepository.findAll();
    }

    /**
     *
     * @param facturaDetalleId
     * @return
     */
    public Optional<FacturaDetalle> getFacturaDetalleById(Long facturaDetalleId) {
        return facturaDetalleRepository.findById(facturaDetalleId);
    }

    /**
     *
     * @param facturaDetalle
     * @return
     */
    public FacturaDetalle saveOrUpdateFacturaDetalle(FacturaDetalle facturaDetalle) {
        return facturaDetalleRepository.save(facturaDetalle);
    }

    /**
     *
     * @param facturaDetalleId
     * @return
     */
    public boolean deleteFacturaDetalle(Long facturaDetalleId) {
        try {
            facturaDetalleRepository.deleteById(facturaDetalleId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
