package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.dto.ClienteDTO;
import com.softlond.store.dominio.excepciones.ClienteNoExistenteException;
import com.softlond.store.repositorio.entidades.ClienteDAO;
import com.softlond.store.repositorio.RepositorioCliente;
import com.softlond.store.repositorio.mappers.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCliente {
    private final RepositorioCliente repositorioCliente;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ServicioCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
        this.clienteMapper = new ClienteMapper();
    }

    public Optional<ClienteDTO> consultarClientePorCedula(int cedula){
        Optional<ClienteDAO> clienteDAO = this.repositorioCliente.findById(cedula);
        if (clienteDAO.isPresent()) {
            return Optional.of(clienteMapper.transformarClienteDAOADTO(clienteDAO.get()));
        } else {
            return Optional.empty();
        }
    }
    public List<ClienteDTO> mostrarClientes() {
        return clienteMapper.transformarListaDeClientesDAOADTO(
                (List<ClienteDAO>) this.repositorioCliente.findAll()
        );
    }

    public void crearCliente(ClienteDTO clienteDTO) {
        this.repositorioCliente.save(clienteMapper.transformarADAO(clienteDTO));
    }

    public void actualizarCliente(ClienteDTO clienteDTO) throws ClienteNoExistenteException {
        Optional<ClienteDAO> cliente = this.repositorioCliente.findById(clienteDTO.getCedula());
        if(cliente.isPresent()){
            cliente.get().setNombre(clienteDTO.getNombre());
            cliente.get().setCelular(clienteDTO.getCelular());
            cliente.get().setCorreo(clienteDTO.getCorreo());
            this.repositorioCliente.save(cliente.get());
        }else{
            throw new ClienteNoExistenteException();
        }
    }

    public void eliminarCliente(int id) {
        this.repositorioCliente.deleteById(id);
    }
}
