package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Model.Categoria;
import com.utn.tsp.proyectofinal.Services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/categoria")
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
     * @param categoriaId
     * @return
     */
    @GetMapping("/{categoriaId}")
    public Optional<Categoria> getCategoriaById(@PathVariable("categoriaId") Long categoriaId) {
        return categoriaService.getCategoriaById(categoriaId);
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

    /**
     *
     * @param categoriaId
     * @return
     */
    @DeleteMapping("/{categoriaId}")
    public String deleteCliente(@PathVariable("categoriaId") Long categoriaId) {
        boolean eliminado = categoriaService.deleteCategoria(categoriaId);
        if (eliminado) {
            return "Se eliminó la Categoría con id: " + categoriaId;
        }
        else {
            return "No se encontró la Categoría con id: " + categoriaId;
        }
    }

}