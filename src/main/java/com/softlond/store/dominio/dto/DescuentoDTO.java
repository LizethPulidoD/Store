package com.softlond.store.dominio.dto;

public class DescuentoDTO {
    private Long id;
    private float porcentajeDescuento;

    public DescuentoDTO(Long id, float porcentajeDescuento) {
        this.id = id;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
