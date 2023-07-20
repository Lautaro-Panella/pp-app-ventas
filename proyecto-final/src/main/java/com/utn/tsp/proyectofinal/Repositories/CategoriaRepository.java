package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByFechaBajaNull();

    @Query(value = "SELECT * FROM CATEGORIA R0 WHERE R0.DESCRIPCION LIKE :texto", nativeQuery = true)
    List<Categoria> findCategoriasByDescripcion(@Param("texto") String texto);

}
