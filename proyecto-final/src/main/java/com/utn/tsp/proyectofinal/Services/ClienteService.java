package com.utn.tsp.proyectofinal.Services;

import com.utn.tsp.proyectofinal.Model.Cliente;
import com.utn.tsp.proyectofinal.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     *
     * @return
     */
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    /**
     *
     * @return
     */
    public List<Cliente> getClientesActivos() {
        return clienteRepository.findByFechaBajaNull();
    }

    /**
     *
     * @param clienteId
     * @return
     */
    public Optional<Cliente> getClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }

    /**
     *
     * @param texto
     * @return
     */
    public List<Cliente> getClientesByNombreOrApellidoOrDni(String texto) {
        if(texto != null) {
            texto = "%" + texto.toLowerCase() + "%";
        }
        return clienteRepository.findClientesByNombreOrApellidoOrDni(texto);
    }

    /**
     *
     * @param cliente
     * @return
     */
    public Cliente saveOrUpdateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
