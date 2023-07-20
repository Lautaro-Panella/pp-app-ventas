package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Model.Categoria;
import com.utn.tsp.proyectofinal.Services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/categoria")
@CrossOrigin("http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    /**
     *
     * @return
     */
    @GetMapping
    public List<Categoria> getCategorias() {
        return categoriaService.getCategorias();
    }

    /**
     *
     * @return
     */
    @GetMapping("/activos")
    public List<Categoria> getCategoriasActivas() {
        return categoriaService.getCategoriasActivas();
    }

    /**
     *
     * @param categoriaId
     * @return
     */
    @GetMapping("/{categoriaId}")
    public Optional<Categoria> getCategoriaById(@PathVariable("categoriaId") Long categoriaId) {
        return categoriaService.getCategoriaById(categoriaId);
    }

    /**
     *
     * @param texto
     * @return
     */
    @GetMapping("/customFilter")
    public List<Categoria> getCategoriasByDescripcion(@RequestParam("texto") String texto) {
        return categoriaService.getCategoriasByDescripcion(texto);
    }

    /**
     *
     * @param categoria
     * @return
     */
    @PostMapping
    public Categoria saveOrUpdateCategoria(@RequestBody Categoria categoria) {
        return categoriaService.saveOrUpdateCategoria(categoria);
    }

}