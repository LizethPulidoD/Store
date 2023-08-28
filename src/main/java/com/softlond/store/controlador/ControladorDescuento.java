package com.softlond.store.controlador;

import com.softlond.store.dominio.dto.DescuentoDTO;
import com.softlond.store.dominio.servicios.ServicioDescuento;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/descuento")
public class ControladorDescuento {
    private final ServicioDescuento servicioDescuento;

    public ControladorDescuento(ServicioDescuento servicioDescuento) {
        this.servicioDescuento = servicioDescuento;
    }

    @PostMapping
    public void crearDescuento(@RequestBody  DescuentoDTO descuentoDTO){
        servicioDescuento.crearDescuento(descuentoDTO);
    }

    @PutMapping
    public void actualizarDescuento(@RequestBody DescuentoDTO descuentoDTO){
        servicioDescuento.crearDescuento(descuentoDTO);
    }
}
