package com.utn.tsp.proyectofinal.Services;

import com.utn.tsp.proyectofinal.Model.Categoria;
import com.utn.tsp.proyectofinal.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     *
     * @return
     */
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     *
     * @return
     */
    public List<Categoria> getCategoriasActivas() { return categoriaRepository.findByFechaBajaNull(); }

    /**
     *
     * @param categoriaId
     * @return
     */
    public Optional<Categoria> getCategoriaById(Long categoriaId) {
        return categoriaRepository.findById(categoriaId);
    }

    /**
     *
     * @param texto
     * @return
     */
    public List<Categoria> getCategoriasByDescripcion(String texto) {
        if(texto != null) {
            texto = "%" + texto.toLowerCase() + "%";
        }
        return categoriaRepository.findCategoriasByDescripcion(texto);
    }

    /**
     *
     * @param categoria
     * @return
     */
    public Categoria saveOrUpdateCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

}
