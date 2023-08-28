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

    @ManyToOne
    @JoinColumn(name = "cedula")
    private ClienteDAO clienteDAO;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private Date fecha;

    @OneToMany(mappedBy = "productoDAO")
    private List<ProductoVentaDAO> ventasProductos = new ArrayList<>();

    public VentaDAO() {
    }

    public VentaDAO(Long idVenta, ClienteDAO clienteDAO, Date fecha, List<ProductoVentaDAO> ventasProductos) {
        this.idVenta = idVenta;
        this.clienteDAO = clienteDAO;
        this.fecha = fecha;
        this.ventasProductos = ventasProductos;
    }

    public VentaDAO(Long idVenta, ClienteDAO clienteDAO, Date fecha) {
        this.idVenta = idVenta;
        this.clienteDAO = clienteDAO;
        this.fecha = fecha;
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

}
