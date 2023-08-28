package com.softlond.store.controlador;

import com.softlond.store.dominio.dto.comandos.ClienteDTO;
import com.softlond.store.dominio.excepciones.ClienteNoExistenteException;
import com.softlond.store.repositorio.entidades.ClienteDAO;
import com.softlond.store.dominio.servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clientes")
public class ControladorCliente {
    private final ServicioCliente servicioCliente;

    @Autowired
    public ControladorCliente(ServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    @GetMapping
    public List<ClienteDTO> mostrarClientes() {
        return this.servicioCliente.mostrarClientes();
    }

    @PostMapping
    public void crearCliente(@RequestBody ClienteDTO clienteDTO) {
        this.servicioCliente.crearCliente(clienteDTO);
    }

    @PutMapping
    public String actualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            this.servicioCliente.actualizarCliente(clienteDTO);
            return "Cliente actualizado correctamente.";
        } catch (ClienteNoExistenteException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping(path = "{cedulaCliente}")
    public void eliminarCliente(@PathVariable("cedulaCliente") int cedula) {
        this.servicioCliente.eliminarCliente(cedula);
    }

}
