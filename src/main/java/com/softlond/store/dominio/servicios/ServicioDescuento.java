package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.dto.DescuentoDTO;
import com.softlond.store.dominio.excepciones.DescuentoNoExistenteException;
import com.softlond.store.repositorio.RepositorioDescuento;
import com.softlond.store.repositorio.entidades.DescuentoDAO;
import com.softlond.store.repositorio.mappers.DescuentoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDescuento {
    private final RepositorioDescuento repositorioDescuento;
    private final ServicioVenta servicioVenta;
    private final DescuentoMapper descuentoMapper;

    public ServicioDescuento(RepositorioDescuento repositorioDescuento, ServicioVenta servicioVenta) {
        this.repositorioDescuento = repositorioDescuento;
        this.servicioVenta = servicioVenta;
        this.descuentoMapper = new DescuentoMapper();
    }

    public DescuentoDTO obtenerDescuento(Long id) throws DescuentoNoExistenteException {
        Optional<DescuentoDAO> descuento = this.repositorioDescuento.findById(id);
        if(descuento.isPresent()){
            return descuentoMapper.transformarADTO(descuento.get());
        }else{
            throw new DescuentoNoExistenteException();
        }
    }

    public void crearDescuento(DescuentoDTO descuentoDTO) {
        Optional<DescuentoDAO> descuento = this.repositorioDescuento.findById(1L);
        if (descuento.isPresent()) {
            actualizarDescuento(descuento.get(), descuentoDTO);
        } else {
            DescuentoDAO nuevoDescuento = descuentoMapper.transformarADAO(descuentoDTO);
            nuevoDescuento.setIdDescuento(1L);
            this.repositorioDescuento.save(nuevoDescuento);
        }
    }

    public void actualizarDescuento(DescuentoDAO descuentoExistente, DescuentoDTO descuentoDTO) {
        descuentoExistente.setPorcentajeDescuento(descuentoDTO.getPorcentajeDescuento());
        this.repositorioDescuento.save(descuentoExistente);
    }

    public double obtenerDescuentoDeCliente(Double totalEnVentas){

    }
}
