package com.softlond.store.dominio.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class VentaPeticionDTO {
    private int cedulaCliente;
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
