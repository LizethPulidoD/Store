package com.softlond.store.repositorio.entidades;

import javax.persistence.*;

@Entity
@Table(name = "Cantidad")
public class ProductoVentaDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoDAO productoDAO;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private VentaDAO ventaDAO;

    @Column(name = "cantidad")
    private Long cantidad;


    public ProductoVentaDAO() {
    }

    public ProductoVentaDAO(ProductoDAO productoDAO, VentaDAO ventaDAO, Long cantidad) {
        this.productoDAO = productoDAO;
        this.ventaDAO = ventaDAO;
        this.cantidad = cantidad;
    }

    public ProductoDAO getProducto() {
        return productoDAO;
    }

    public void setProducto(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public VentaDAO getVenta() {
        return ventaDAO;
    }

    public void setVenta(VentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
