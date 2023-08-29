package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.dto.DescuentoDTO;
import com.softlond.store.dominio.excepciones.DescuentoNoExistenteException;
import com.softlond.store.repositorio.RepositorioDescuento;
import com.softlond.store.repositorio.entidades.DescuentoDAO;
import com.softlond.store.repositorio.mappers.DescuentoMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioDescuento {
    private final Double COMPRA_MINIMA = 1000000d;
    private final RepositorioDescuento repositorioDescuento;
    private final DescuentoMapper descuentoMapper;

    public ServicioDescuento(RepositorioDescuento repositorioDescuento) {
        this.repositorioDescuento = repositorioDescuento;
        this.descuentoMapper = new DescuentoMapper();
    }

    public DescuentoDTO obtenerDescuento() throws DescuentoNoExistenteException {
        Optional<DescuentoDAO> descuento = this.repositorioDescuento.findById(1L);
        if (descuento.isPresent()) {
            return descuentoMapper.transformarADTO(descuento.get());
        } else {
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

    public double obtenerDescuentoDeCliente(Double totalEnVentas) throws DescuentoNoExistenteException {
        if (totalEnVentas >= COMPRA_MINIMA) {
            return obtenerDescuento().getPorcentajeDescuento();
        } else {
            return 0;
        }
    }
}
