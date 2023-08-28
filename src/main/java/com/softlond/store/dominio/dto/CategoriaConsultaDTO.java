package com.softlond.store.dominio.dto;

public class CategoriaConsultaDTO {
    private String nombre;

    public CategoriaConsultaDTO(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaConsultaDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
