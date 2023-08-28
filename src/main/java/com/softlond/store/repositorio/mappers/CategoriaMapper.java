package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.CategoriaConsultaDTO;
import com.softlond.store.dominio.dto.CategoriaDTO;
import com.softlond.store.repositorio.entidades.CategoriaDAO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {
    public CategoriaDAO transformarADAO(CategoriaConsultaDTO categoriaDTO){
        return new CategoriaDAO(categoriaDTO.getNombre());
    }

    public CategoriaDTO transformarADTO(CategoriaDAO categoriaDAO){
        return new CategoriaDTO(categoriaDAO.getNombre());
    }

    public CategoriaDAO transformarConsultaADAO(CategoriaConsultaDTO categoriaConsultaDTO){
        return new CategoriaDAO(categoriaConsultaDTO.getId(), categoriaConsultaDTO.getNombre());
    }

    public CategoriaConsultaDTO transformarConsultaADTO(CategoriaDAO categoriaDAO){
        return new CategoriaConsultaDTO(categoriaDAO.getId(),categoriaDAO.getNombre());
    }


    public List<CategoriaDTO> transformarListaADTO(List<CategoriaDAO> categoriasDAO){
        return categoriasDAO.stream().map(this::transformarADTO).collect(Collectors.toList());
    }
}
