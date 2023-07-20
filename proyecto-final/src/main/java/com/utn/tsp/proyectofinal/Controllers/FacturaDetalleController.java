package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Model.FacturaDetalle;
import com.utn.tsp.proyectofinal.Services.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/facturaDetalle")
@CrossOrigin("http://localhost:4200")
public class FacturaDetalleController {

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    /**
     *
     * @return
     */
    @GetMapping
    public List<FacturaDetalle> getFacturaDetalles() {
        return facturaDetalleService.getFacturaDetalles();
    }

    /**
     *
     * @param facturaDetalleId
     * @return
     */
    @GetMapping("/{facturaDetalleId}")
    public Optional<FacturaDetalle> getFacturaDetalleById(@PathVariable("facturaDetalleId") Long facturaDetalleId) {
        return facturaDetalleService.getFacturaDetalleById(facturaDetalleId);
    }

    /**
     *
     * @param facturaId
     * @return
     */
    @GetMapping("/customFilter")
    public List<FacturaDetalle> getFacturaDetallesByFacturaId(@RequestParam("facturaId") Long facturaId) {
        return facturaDetalleService.getFacturaDetallesByFacturaId(facturaId);
    }

    /**
     *
     * @param facturaDetalle
     * @return
     */
    @PostMapping
    public FacturaDetalle saveOrUpdateFacturaDetalle(@RequestBody FacturaDetalle facturaDetalle) {
        return facturaDetalleService.saveOrUpdateFacturaDetalle(facturaDetalle);
    }

}
