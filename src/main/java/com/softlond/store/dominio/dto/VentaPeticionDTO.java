package com.softlond.store.dominio.dto;

import java.util.Date;
import java.util.List;

public class VentaPeticionDTO {
    private int cedulaCliente;
    private Date fecha;
    private List<ProductoVentaPeticionDTO> productos;

    public VentaPeticionDTO(int cedulaCliente, Date fecha, List<ProductoVentaPeticionDTO> productos) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ProductoVentaPeticionDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoVentaPeticionDTO> productos) {
        this.productos = productos;
    }
}
