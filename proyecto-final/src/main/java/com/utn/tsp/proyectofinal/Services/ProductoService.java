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
     * @param productoId
     * @return
     */
    public Optional<Producto> getProductoById(Long productoId) {
        return productoRepository.findById(productoId);
    }

    /**
     *
     * @param producto
     * @return
     */
    public Producto saveOrUpdateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     *
     * @param productoId
     * @return
     */
    public boolean deleteProducto(Long productoId) {
        try {
            productoRepository.deleteById(productoId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
