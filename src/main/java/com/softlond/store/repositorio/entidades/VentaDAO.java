package com.softlond.store.repositorio.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
public class VentaDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne()
    @JoinColumn(name = "cedula")
    private ClienteDAO clienteDAO;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToMany(mappedBy = "ventaDAO")
    private List<ProductoVentaDAO> ventasProductos = new ArrayList<>();

    private double total;

    public VentaDAO() {
    }

    public VentaDAO(Long idVenta, ClienteDAO clienteDAO, Date fecha, List<ProductoVentaDAO> ventasProductos, double total) {
        this.idVenta = idVenta;
        this.clienteDAO = clienteDAO;
        this.fecha = fecha;
        this.ventasProductos = ventasProductos;
        this.total = total;
    }

    public VentaDAO(Long idVenta, ClienteDAO clienteDAO, Date fecha) {
        this.idVenta = idVenta;
        this.clienteDAO = clienteDAO;
        this.fecha = fecha;
    }

    public VentaDAO(ClienteDAO clienteDAO, Date fecha) {
        this.clienteDAO = clienteDAO;
        this.setFecha(fecha);
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<ProductoVentaDAO> getVentasProductos() {
        return ventasProductos;
    }

    public void setVentasProductos(List<ProductoVentaDAO> ventasProductos) {
        this.ventasProductos = ventasProductos;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClienteDAO getCliente() {
        return clienteDAO;
    }

    public void setCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
