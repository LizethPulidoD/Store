package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.ProductoVentaConsultaDTO;
import com.softlond.store.repositorio.entidades.ProductoVentaDAO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoVentaMapper {

    public ProductoVentaConsultaDTO transformarProductoVentaAProductoVentaConsultaDTO(ProductoVentaDAO productoVentaDAO){
        ProductoVentaConsultaDTO productoVentaConsultaDTO = new ProductoVentaConsultaDTO();
        productoVentaConsultaDTO.setCantidad(productoVentaDAO.getCantidad());
        productoVentaConsultaDTO.setCategoria(productoVentaDAO.getProducto().getCategoria().getNombre());
        productoVentaConsultaDTO.setNombre(productoVentaDAO.getProducto().getNombre());
        productoVentaConsultaDTO.setPrecio(productoVentaDAO.getProducto().getPrecio());
        return productoVentaConsultaDTO;
    }

    public List<ProductoVentaConsultaDTO> transformarListaProductoVentaAListaProductoVentaConsultaDTO(List<ProductoVentaDAO> productoVentaDAOS){
        return productoVentaDAOS.stream().map(this::transformarProductoVentaAProductoVentaConsultaDTO).collect(Collectors.toList());
    }
}
