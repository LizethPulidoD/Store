package com.softlond.store.dominio.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VentaConsultaDTO {

    private Long idVenta;
    private int cedulaCliente;
    private LocalDate fecha;
    private List<ProductoVentaConsultaDTO> productos;


    public VentaConsultaDTO() {
    }

    public VentaConsultaDTO(Long idVenta, int cedulaCliente, LocalDate fecha, List<ProductoVentaConsultaDTO> productos) {
        this.idVenta = idVenta;
        this.cedulaCliente = cedulaCliente;
        this.fecha = fecha;
        this.productos = productos;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<ProductoVentaConsultaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoVentaConsultaDTO> productos) {
        this.productos = productos;
    }

}
