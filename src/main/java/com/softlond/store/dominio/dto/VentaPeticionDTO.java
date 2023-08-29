package com.softlond.store.dominio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class VentaPeticionDTO {
    private int cedulaCliente;
    @JsonFormat(pattern = "dd/MM/yyyy") // Especifica el formato de fecha
    private LocalDate fecha;
    private List<ProductoVentaPeticionDTO> productos;

    public VentaPeticionDTO(int cedulaCliente, LocalDate fecha, List<ProductoVentaPeticionDTO> productos) {
        this.cedulaCliente = cedulaCliente;
        this.fecha = fecha;
        this.productos = productos;
    }

    public VentaPeticionDTO() {
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

    public List<ProductoVentaPeticionDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoVentaPeticionDTO> productos) {
        this.productos = productos;
    }
}
