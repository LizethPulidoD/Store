package com.softlond.store.dominio.dto;

public class ProductoVentaConsultaDTO {
    private String nombre;
    private Double precio;
    private String categoria;

    private Long cantidad;

    public ProductoVentaConsultaDTO(String nombre, Double precio, String categoria, Long cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public ProductoVentaConsultaDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
