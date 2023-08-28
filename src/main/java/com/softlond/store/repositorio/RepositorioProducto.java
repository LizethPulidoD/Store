package com.softlond.store.repositorio;

import com.softlond.store.repositorio.entidades.ProductoDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioProducto extends CrudRepository<ProductoDAO, Long> {

    @Query(value = "SELECT * FROM PRODUCTO WHERE PRODUCTO.NOMBRE = :nombre", nativeQuery = true)
    Optional<ProductoDAO> consultarProductoPorNombre(@Param("nombre") String nombre);
}
