package com.softlond.store.dominio.servicios;

import com.softlond.store.repositorio.entidades.ProductoVentaDAO;
import com.softlond.store.repositorio.RepositorioVentasProducto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioVentasProducto {
    private final RepositorioVentasProducto repositorioVentasProducto;

    public ServicioVentasProducto(RepositorioVentasProducto repositorioVentasProducto) {
        this.repositorioVentasProducto = repositorioVentasProducto;
    }

    public void guardarVentasProducto(ProductoVentaDAO productoVentaDAO) {
        repositorioVentasProducto.save(productoVentaDAO);
    }

    public void guardarVentasProducto(List<ProductoVentaDAO> cantidadProductosVenta) {
        repositorioVentasProducto.saveAll(cantidadProductosVenta);
    }

    public List<ProductoVentaDAO> obtenerVentasProducto() {
        return (List<ProductoVentaDAO>) repositorioVentasProducto.findAll();
    }

    public List<ProductoVentaDAO> obtenerVentasProductoPorIdDeVenta(Long idVenta) {
        return (List<ProductoVentaDAO>) repositorioVentasProducto.findByIdVenta(idVenta);
    }
}
