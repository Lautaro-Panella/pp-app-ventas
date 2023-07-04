package com.utn.tsp.proyectofinal.Repositories;

import com.utn.tsp.proyectofinal.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByFechaBajaNull();
}
