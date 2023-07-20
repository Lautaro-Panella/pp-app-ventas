package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByFechaBajaNull();

    @Query(value = "SELECT * FROM PRODUCTO R0 WHERE R0.DESCRIPCION LIKE :texto", nativeQuery = true)
    List<Producto> findProductosByDescripcion(@Param("texto") String texto);

}
