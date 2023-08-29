package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.VentaConsultaDTO;
import com.softlond.store.repositorio.entidades.VentaDAO;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class VentaMapper {

    private final ProductoVentaMapper productoVentaMapper;

    public VentaMapper() {
        productoVentaMapper = new ProductoVentaMapper();
    }

    public VentaConsultaDTO transformarVentaADTOParaConsulta(VentaDAO ventaDAO){
        VentaConsultaDTO ventaConsultaDTO = new VentaConsultaDTO();
        ventaConsultaDTO.setIdVenta(ventaDAO.getIdVenta());
        ventaConsultaDTO.setCedulaCliente(ventaDAO.getCliente().getCedula());
        ventaConsultaDTO.setFecha(Instant.ofEpochMilli(ventaDAO.getFecha().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        ventaConsultaDTO.setProductos(productoVentaMapper.transformarListaProductoVentaAListaProductoVentaConsultaDTO(ventaDAO.getVentasProductos()));
        ventaConsultaDTO.setTotal(ventaDAO.getTotal());
        return ventaConsultaDTO;
    }

    public List<VentaConsultaDTO> transformarListaDeVentasADTOParaConsulta(List<VentaDAO> ventasDAO){
        return ventasDAO.stream().map(this::transformarVentaADTOParaConsulta).collect(Collectors.toList());
    }
}
