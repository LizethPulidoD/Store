package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.ProductoConsultaDTO;
import com.softlond.store.dominio.dto.CategoriaDTO;
import com.softlond.store.dominio.dto.ProductoDTO;
import com.softlond.store.dominio.dto.ProductoPeticionDTO;
import com.softlond.store.repositorio.entidades.CategoriaDAO;
import com.softlond.store.repositorio.entidades.ProductoDAO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoMapper {
    private final CategoriaMapper categoriaMapper;

    public ProductoMapper() {
        categoriaMapper = new CategoriaMapper();
    }

    public ProductoDAO transformarADAO(ProductoDTO productoDTO) {
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.setNombre(productoDTO.getNombre());
        productoDAO.setPrecio(productoDTO.getPrecio());
        productoDAO.setId(productoDTO.getId());
        productoDAO.setCategoria(categoriaMapper.transformarADAO(productoDTO.getCategoriaDAO()));
        return productoDAO;
    }

    public ProductoDAO transformarADAO(ProductoPeticionDTO productoDTO, CategoriaDTO categoriaDTO) {
        CategoriaDAO categoriaDAO = categoriaMapper.transformarConsultaADAO(categoriaDTO);
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.setCategoria(categoriaDAO);
        productoDAO.setPrecio(productoDTO.getPrecio());
        productoDAO.setNombre(productoDTO.getNombre());
        return productoDAO;
    }

    public ProductoConsultaDTO transformarAConsultaDTO(ProductoDAO productoDAO) {
        return new ProductoConsultaDTO(productoDAO.getNombre(), productoDAO.getPrecio(), productoDAO.getCategoria().getNombre());
    }

    public ProductoDTO transformarADTO(ProductoDAO productoDAO) {
        return new ProductoDTO(productoDAO.getId(), categoriaMapper.transformarADTO(productoDAO.getCategoria()), productoDAO.getNombre(), productoDAO.getPrecio());
    }

    public List<ProductoConsultaDTO> transformarListaAConsultaDTO(List<ProductoDAO> productoDAO) {
        return productoDAO.stream().map(this::transformarAConsultaDTO).collect(Collectors.toList());
    }
}
