package com.utn.tsp.proyectofinal.Services;

import com.utn.tsp.proyectofinal.Model.Producto;
import com.utn.tsp.proyectofinal.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     *
     * @return
     */
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    /**
     *
     * @return
     */
    public List<Producto> getProductosActivos() { return productoRepository.findByFechaBajaNull(); }

    /**
     *
     * @param productoId
     * @return
     */
    public Optional<Producto> getProductoById(Long productoId) {
        return productoRepository.findById(productoId);
    }

    /**
     *
     * @param texto
     * @return
     */
    public List<Producto> getProductosByDescripcion(String texto) {
        if(texto != null) {
            texto = "%" + texto.toLowerCase() + "%";
        }
        return productoRepository.findProductosByDescripcion(texto);
    }

    /**
     *
     * @param producto
     * @return
     */
    public Producto saveOrUpdateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

}
