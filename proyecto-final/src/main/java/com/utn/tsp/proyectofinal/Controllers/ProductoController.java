package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Model.Producto;
import com.utn.tsp.proyectofinal.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/producto")
@CrossOrigin("http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     *
     * @return
     */
    @GetMapping
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }

    /**
     *
     * @return
     */
    @GetMapping("/activos")
    public List<Producto> getProductosActivos() {
        return productoService.getProductosActivos();
    }

    /**
     *
     * @param productoId
     * @return
     */
    @GetMapping("/{productoId}")
    public Optional<Producto> getProductoById(@PathVariable("productoId") Long productoId) {
        return productoService.getProductoById(productoId);
    }

    /**
     *
     * @param texto
     * @return
     */
    @GetMapping("/customFilter")
    public List<Producto> getProductosByDescripcion(@RequestParam("texto") String texto) {
        return productoService.getProductosByDescripcion(texto);
    }

    /**
     *
     * @param producto
     * @return
     */
    @PostMapping
    public Producto saveOrUpdateProducto(@RequestBody Producto producto) {
        return productoService.saveOrUpdateProducto(producto);
    }

}
