package com.softlond.store.repositorio.entidades;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteDAO {
    @Id
    private int cedula;
    private String nombre;
    private int celular;
    private String correo;

    public ClienteDAO() {

    }

    public ClienteDAO(int cedula, String nombre, int celular, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
