package com.softlond.store.dominio.dto;

public class ProductoDTO {
    private Long id;
    private CategoriaDTO categoriaDAO;
    private String nombre;
    private Double precio;

    public ProductoDTO(Long id, CategoriaDTO categoriaDAO, String nombre, Double precio) {
        this.id = id;
        this.categoriaDAO = categoriaDAO;
        this.nombre = nombre;
        this.precio = precio;
    }

    public ProductoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoriaDTO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDTO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
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
