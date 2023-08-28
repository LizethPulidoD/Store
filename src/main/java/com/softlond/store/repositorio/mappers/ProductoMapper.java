package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.ProductoConsultaDTO;
import com.softlond.store.dominio.dto.comandos.CategoriaConsultaDTO;
import com.softlond.store.dominio.dto.comandos.CategoriaDTO;
import com.softlond.store.dominio.dto.comandos.ProductoPeticionDTO;
import com.softlond.store.repositorio.entidades.CategoriaDAO;
import com.softlond.store.repositorio.entidades.ProductoDAO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoMapper {
    private final CategoriaMapper categoriaMapper;

    public ProductoMapper() {
        categoriaMapper = new CategoriaMapper();
    }

    public ProductoDAO transformarADAO(ProductoConsultaDTO productoConsultaDTO) {
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.setNombre(productoConsultaDTO.getNombre());
        productoDAO.setPrecio(productoConsultaDTO.getPrecio());
        return productoDAO;
    }

    public ProductoDAO transformarADAO(ProductoPeticionDTO productoDTO, CategoriaConsultaDTO categoriaDTO)  {
        CategoriaDAO categoriaDAO = categoriaMapper.transformarConsultaADAO(categoriaDTO);
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.setCategoria(categoriaDAO);
        productoDAO.setPrecio(productoDTO.getPrecio());
        productoDAO.setNombre(productoDTO.getNombre());
        return productoDAO;
    }

    public ProductoConsultaDTO transformarADTO(ProductoDAO productoDAO) {
        return new ProductoConsultaDTO(productoDAO.getNombre(), productoDAO.getPrecio(), productoDAO.getCategoria().getNombre());
    }

    public List<ProductoConsultaDTO> transformarListaADTO(List<ProductoDAO> productoDAO) {
        return productoDAO.stream().map(this::transformarADTO).collect(Collectors.toList());
    }
}
