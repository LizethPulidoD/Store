package com.softlond.store.dominio.dto.comandos;

public class CategoriaActualizarDTO {
    private String antiguoNombre;
    private String nuevoNombre;

    public CategoriaActualizarDTO(String antiguoNombre, String nuevoNombre) {
        this.antiguoNombre = antiguoNombre;
        this.nuevoNombre = nuevoNombre;
    }

    public String getAntiguoNombre() {
        return antiguoNombre;
    }

    public void setAntiguoNombre(String antiguoNombre) {
        this.antiguoNombre = antiguoNombre;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }
}
