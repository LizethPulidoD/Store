package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.comandos.ClienteDTO;
import com.softlond.store.repositorio.entidades.ClienteDAO;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public List<ClienteDTO> transformarListaDeClientesDAOADTO(List<ClienteDAO> clientesDAO) {
        return clientesDAO
                .stream()
                .map(clienteDAO -> {
                    return transformarClienteDAOADTO(clienteDAO);
                }).collect(Collectors.toList());
    }

    public ClienteDTO transformarClienteDAOADTO(ClienteDAO clienteDAO){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCedula(clienteDAO.getCedula());
        clienteDTO.setNombre(clienteDAO.getNombre());
        clienteDTO.setCelular(clienteDAO.getCelular());
        clienteDTO.setCorreo(clienteDAO.getCorreo());
        return clienteDTO;
    }

    public ClienteDAO transformarADAO(ClienteDTO clienteDTO) {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.setCedula(clienteDTO.getCedula());
        clienteDAO.setNombre(clienteDTO.getNombre());
        clienteDAO.setCelular(clienteDTO.getCelular());
        clienteDAO.setCorreo(clienteDTO.getCorreo());
        return clienteDAO;
    }
}
