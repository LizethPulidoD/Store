package com.softlond.store.dominio.dto.comandos;

public class CategoriaDTO {
    private String nombre;

    public CategoriaDTO(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
