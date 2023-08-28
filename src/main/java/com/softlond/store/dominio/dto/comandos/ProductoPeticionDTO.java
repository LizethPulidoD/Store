package com.softlond.store.dominio.dto.comandos;

public class ProductoPeticionDTO {
    private Long idCategoria;
    private String nombre;
    private Double precio;

    public ProductoPeticionDTO(Long idCategoria, String nombre, Double precio) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
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
}
