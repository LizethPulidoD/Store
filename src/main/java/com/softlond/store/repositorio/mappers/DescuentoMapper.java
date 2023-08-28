package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.DescuentoDTO;
import com.softlond.store.repositorio.entidades.DescuentoDAO;

public class DescuentoMapper {
    public DescuentoDTO transformarADTO(DescuentoDAO descuentoDAO){
        return new DescuentoDTO(descuentoDAO.getIdDescuento(),descuentoDAO.getPorcentajeDescuento());
    }

    public DescuentoDAO transformarADAO(DescuentoDTO descuentoDTO){
        return new DescuentoDAO(descuentoDTO.getId(),descuentoDTO.getPorcentajeDescuento());
    }
}
