package com.softlond.store.dominio.dto;

public class ProductoVentaPeticionDTO {
    private Long idProducto;
    private Long cantidad;

    public ProductoVentaPeticionDTO(Long idProducto, Long cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public ProductoVentaPeticionDTO() {
    }


    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
