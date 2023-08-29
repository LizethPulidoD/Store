package com.softlond.store.repositorio.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto")
public class ProductoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaDAO categoriaDAO;

    @Column(unique = true)
    private String nombre;
    private Double precio;

    @OneToMany(mappedBy = "productoDAO")
    private List<ProductoVentaDAO> productosVendidos = new ArrayList<>();

    public ProductoDAO() {}

    public ProductoDAO(Long id, CategoriaDAO categoriaDAO, String nombre, Double precio, List<ProductoVentaDAO> productosVendidos) {
        this.id = id;
        this.categoriaDAO = categoriaDAO;
        this.nombre = nombre;
        this.precio = precio;
        this.productosVendidos = productosVendidos;
    }

    public ProductoDAO(Long id, CategoriaDAO idCategoriaDAO, String nombre, Double precio) {
        this.id = id;
        this.categoriaDAO = idCategoriaDAO;
        this.nombre = nombre;
        this.precio = precio;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public List<ProductoVentaDAO> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoVentaDAO> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoriaDAO getCategoria() {
        return categoriaDAO;
    }

    public void setCategoria(CategoriaDAO categoriaDAO) {
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
