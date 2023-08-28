package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.dto.comandos.CategoriaActualizarDTO;
import com.softlond.store.dominio.dto.comandos.CategoriaConsultaDTO;
import com.softlond.store.dominio.dto.comandos.CategoriaDTO;
import com.softlond.store.dominio.excepciones.CategoriaNoExistenteException;
import com.softlond.store.repositorio.RepositorioCategoria;
import com.softlond.store.repositorio.entidades.CategoriaDAO;
import com.softlond.store.repositorio.mappers.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServicioCategoria {

    private final RepositorioCategoria repositorioCategoria;
    private final CategoriaMapper categoriaMapper;


    @Autowired
    public ServicioCategoria(RepositorioCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
        this.categoriaMapper = new CategoriaMapper();
    }

    public List<CategoriaDTO> mostrarCategorias() {
        return categoriaMapper.transformarListaADTO((List<CategoriaDAO>) this.repositorioCategoria.findAll());
    }

    public void crearCategoria(CategoriaConsultaDTO categoriaDTO) {
        this.repositorioCategoria.save(categoriaMapper.transformarADAO(categoriaDTO));
    }

    public Optional<CategoriaConsultaDTO> consultarCategoriaPorId(Long id) {
        Optional<CategoriaDAO> categoriaDAO = this.repositorioCategoria.findById(id);
        if (categoriaDAO.isPresent()) {
            return Optional.of(categoriaMapper.transformarConsultaADTO(categoriaDAO.get()));
        } else {
            return Optional.empty();
        }
    }

    public void actualizarCategoria(CategoriaActualizarDTO categoriaDTO) throws CategoriaNoExistenteException {
        Optional<CategoriaDAO> categoriaConsultada = this.repositorioCategoria.consultarCategoriaPorNombre(categoriaDTO.getAntiguoNombre());
        if (categoriaConsultada.isPresent()) {
            categoriaConsultada.get().setNombre(categoriaDTO.getNuevoNombre());
            this.repositorioCategoria.save(categoriaConsultada.get());
        }else{
            throw new CategoriaNoExistenteException(categoriaDTO.getAntiguoNombre());
        }
    }

    public void eliminarCategoria(Long id) {
        this.repositorioCategoria.deleteById(id);
    }
}
