package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByFechaBajaNull();

    @Query(value = "SELECT * FROM CLIENTE R0 WHERE R0.NOMBRE LIKE :texto OR R0.APELLIDO LIKE :texto OR R0.DNI LIKE :texto", nativeQuery = true)
    List<Cliente> findClientesByNombreOrApellidoOrDni(@Param("texto") String texto);

}
