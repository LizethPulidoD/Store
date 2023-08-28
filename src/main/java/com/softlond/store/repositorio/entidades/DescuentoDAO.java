package com.softlond.store.repositorio.entidades;

import javax.persistence.*;

@Entity
@Table(name = "descuento")
public class DescuentoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDescuento;
    private float porcentajeDescuento;

    public DescuentoDAO() {

    }

    public DescuentoDAO(Long idDescuento, float porcentajeDescuento) {
        this.idDescuento = idDescuento;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Long idDescuento) {
        this.idDescuento = idDescuento;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
