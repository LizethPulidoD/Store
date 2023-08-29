package com.softlond.store.repositorio.mappers;

import com.softlond.store.dominio.dto.CategoriaDTO;
import com.softlond.store.dominio.dto.CategoriaConsultaDTO;
import com.softlond.store.repositorio.entidades.CategoriaDAO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {
    public CategoriaDAO transformarADAO(CategoriaDTO categoriaDTO){
        return new CategoriaDAO(categoriaDTO.getId(),categoriaDTO.getNombre());
    }

    public CategoriaDTO transformarADTO(CategoriaDAO categoriaDAO){
        return new CategoriaDTO(categoriaDAO.getId(), categoriaDAO.getNombre());
    }

    public CategoriaConsultaDTO transformarAConsultaDTO(CategoriaDAO categoriaDAO){
        return new CategoriaConsultaDTO(categoriaDAO.getNombre());
    }

    public CategoriaDAO transformarConsultaADAO(CategoriaDTO categoriaDTO){
        return new CategoriaDAO(categoriaDTO.getId(), categoriaDTO.getNombre());
    }

    public CategoriaDTO transformarConsultaADTO(CategoriaDAO categoriaDAO){
        return new CategoriaDTO(categoriaDAO.getId(),categoriaDAO.getNombre());
    }


    public List<CategoriaConsultaDTO> transformarListaADTO(List<CategoriaDAO> categoriasDAO){
        return categoriasDAO.stream().map(this::transformarAConsultaDTO).collect(Collectors.toList());
    }
}
