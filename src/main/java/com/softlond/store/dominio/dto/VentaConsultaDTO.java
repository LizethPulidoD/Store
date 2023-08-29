package com.softlond.store.dominio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class VentaConsultaDTO {

    private Long idVenta;
    private int cedulaCliente;
    @JsonFormat(pattern = "dd/MM/yyyy") // Especifica el formato de fecha
    private LocalDate fecha;
    private List<ProductoVentaConsultaDTO> productos;
    private double total;

    public VentaConsultaDTO(Long idVenta, int cedulaCliente, LocalDate fecha, List<ProductoVentaConsultaDTO> productos, double total) {
        this.idVenta = idVenta;
        this.cedulaCliente = cedulaCliente;
        this.fecha = fecha;
        this.productos = productos;
        this.total = total;
    }

    public VentaConsultaDTO() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
