package com.softlond.store.repositorio;

import com.softlond.store.repositorio.entidades.VentaDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RepositorioVenta extends CrudRepository<VentaDAO, Long> {
    @Query(value = "SELECT * FROM venta WHERE venta.cedula = :idcliente", nativeQuery = true)
    List<VentaDAO> obtenerVentasPorCliente(@Param("idcliente") Long idCliente);

    @Query(value = "SELECT * FROM VENTA WHERE VENTA.fecha = :fecha", nativeQuery = true)
    List<VentaDAO> obtenerVentasPorFecha(@Param("fecha") Date fecha);

    @Query(value = "SELECT * FROM VENTA v WHERE v.fecha BETWEEN :fechaInicio AND :fechaFin AND v.cedula = :idcliente", nativeQuery = true)
    List<VentaDAO> obtenerVentasPorRangoDeFechaYCliente(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("idcliente") int idCliente);
}
