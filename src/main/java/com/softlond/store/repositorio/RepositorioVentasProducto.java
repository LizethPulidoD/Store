package com.softlond.store.repositorio;

import com.softlond.store.repositorio.entidades.ProductoVentaDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RepositorioVentasProducto extends CrudRepository<ProductoVentaDAO,Long> {
    @Query(value = "SELECT * FROM CANTIDAD where CANTIDAD.VENTA_ID = :IDVENTA",nativeQuery = true)
    Iterable<ProductoVentaDAO> findByIdVenta(@Param("IDVENTA") Long id);
}
