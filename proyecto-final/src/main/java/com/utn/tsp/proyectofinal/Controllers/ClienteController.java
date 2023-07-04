package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Model.Cliente;
import com.utn.tsp.proyectofinal.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cliente")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     *
     * @return
     */
    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }

    /**
     *
     * @return
     */
    @GetMapping("/activos")
    public List<Cliente> getClientesActivos() {
        return clienteService.getClientesActivos();
    }

    /**
     *
     * @param clienteId
     * @return
     */
    @GetMapping("/{clienteId}")
    public Optional<Cliente> getClienteById(@PathVariable("clienteId") Long clienteId) {
        return clienteService.getClienteById(clienteId);
    }

    /**
     *
     * @param cliente
     * @return
     */
    @PostMapping
    public Cliente saveOrUpdateCliente(@RequestBody Cliente cliente) {
        return clienteService.saveOrUpdateCliente(cliente);
    }

}
