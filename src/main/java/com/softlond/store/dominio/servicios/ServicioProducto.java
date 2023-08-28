package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.dto.ProductoConsultaDTO;
import com.softlond.store.dominio.dto.CategoriaDTO;
import com.softlond.store.dominio.dto.ProductoPeticionDTO;
import com.softlond.store.dominio.excepciones.CategoriaNoExistenteException;
import com.softlond.store.dominio.excepciones.ProductoNoExistenteException;
import com.softlond.store.repositorio.RepositorioProducto;
import com.softlond.store.repositorio.entidades.ProductoDAO;
import com.softlond.store.repositorio.mappers.CategoriaMapper;
import com.softlond.store.repositorio.mappers.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {
    private final RepositorioProducto repositorioProducto;
    private final ServicioCategoria servicioCategoria;
    private final ProductoMapper productoMapper;
    private final CategoriaMapper categoriaMapper;

    @Autowired
    public ServicioProducto(RepositorioProducto repositorioProducto, ServicioCategoria servicioCategoria) {
        this.repositorioProducto = repositorioProducto;
        this.servicioCategoria = servicioCategoria;
        this.productoMapper = new ProductoMapper();
        this.categoriaMapper = new CategoriaMapper();
    }

    public List<ProductoConsultaDTO> mostrarProductos() {
        return productoMapper.transformarListaADTO((List<ProductoDAO>) this.repositorioProducto.findAll());
    }

    public void crearProducto(ProductoPeticionDTO consultaProductoDTO) throws CategoriaNoExistenteException {
        Optional<CategoriaDTO> categoriaDTO = this.servicioCategoria.consultarCategoriaPorId(consultaProductoDTO.getIdCategoria());
        if (categoriaDTO.isPresent()) {
            ProductoDAO productoDAO = productoMapper.transformarADAO(consultaProductoDTO, categoriaDTO.get());
            repositorioProducto.save(productoDAO);
        } else {
            throw new CategoriaNoExistenteException(consultaProductoDTO.getIdCategoria());
        }
    }

    public void actualizarProducto(ProductoPeticionDTO productoDTO) throws ProductoNoExistenteException, CategoriaNoExistenteException {
        Optional<ProductoDAO> producto = this.repositorioProducto.consultarProductoPorNombre(productoDTO.getNombre());
        if (producto.isPresent()) {
            Optional<CategoriaDTO> categoriaDTO = this.servicioCategoria.consultarCategoriaPorId(productoDTO.getIdCategoria());
            if (categoriaDTO.isPresent()) {
                producto.get().setNombre(productoDTO.getNombre());
                producto.get().setPrecio(productoDTO.getPrecio());
                producto.get().setCategoria(categoriaMapper.transformarADAO(categoriaDTO.get()));
                this.repositorioProducto.save(producto.get());
            } else {
                throw new CategoriaNoExistenteException(productoDTO.getIdCategoria());
            }
        } else {
            throw new ProductoNoExistenteException(productoDTO.getNombre());
        }
    }

    public void eliminarProducto(Long id) {
        this.repositorioProducto.deleteById(id);
    }
}
