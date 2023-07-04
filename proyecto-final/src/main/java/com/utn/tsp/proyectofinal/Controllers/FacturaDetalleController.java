package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Services.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/facturaDetalle")
public class FacturaDetalleController {

    @Autowired
    private FacturaDetalleService facturaDetalleService;

}
