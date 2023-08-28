package com.softlond.store.repositorio;


import com.softlond.store.repositorio.entidades.ClienteDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCliente extends CrudRepository<ClienteDAO, Integer> {
}
