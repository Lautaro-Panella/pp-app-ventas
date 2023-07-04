package com.utn.tsp.proyectofinal.Services;

import com.utn.tsp.proyectofinal.Model.Factura;
import com.utn.tsp.proyectofinal.Repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    /**
     *
     * @return
     */
    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    /**
     *
     * @param facturaId
     * @return
     */
    public Optional<Factura> getFacturaById(Long facturaId) {
        return facturaRepository.findById(facturaId);
    }

    /**
     *
     * @param factura
     * @return
     */
    public Factura saveOrUpdateFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    /**
     *
     * @param facturaId
     * @return
     */
    public boolean deleteFactura(Long facturaId) {
        try {
            facturaRepository.deleteById(facturaId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
