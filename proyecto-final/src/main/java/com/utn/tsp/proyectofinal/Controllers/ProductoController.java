package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

}
