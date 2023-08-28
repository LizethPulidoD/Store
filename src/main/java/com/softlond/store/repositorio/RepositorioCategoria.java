package com.softlond.store.repositorio;

import com.softlond.store.repositorio.entidades.CategoriaDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepositorioCategoria extends CrudRepository<CategoriaDAO, Long> {
    @Query(value = "SELECT * FROM CATEGORIA WHERE CATEGORIA.NOMBRE = :nombre", nativeQuery = true)
    Optional<CategoriaDAO> consultarCategoriaPorNombre(@Param("nombre") String nombre);
}
