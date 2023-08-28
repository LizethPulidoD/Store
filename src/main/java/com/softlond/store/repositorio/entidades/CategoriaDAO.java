package com.softlond.store.repositorio.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "categoria")
public class CategoriaDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;

    @OneToMany(mappedBy = "categoriaDAO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoDAO> productos = new ArrayList<>();

    public CategoriaDAO() {

    }

    public CategoriaDAO(Long id, String nombre, List<ProductoDAO> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }

    public CategoriaDAO(Long id, String nombreCategoria) {
        this.id = id;
        this.nombre = nombreCategoria;

    }

    public List<ProductoDAO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDAO> productos) {
        this.productos = productos;
    }

    public CategoriaDAO(String nombreCategoria) {
        this.nombre = nombreCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
