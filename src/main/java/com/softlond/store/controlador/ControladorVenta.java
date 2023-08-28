package com.softlond.store.controlador;

import com.softlond.store.dominio.dto.VentaConsultaDTO;
import com.softlond.store.dominio.dto.VentaPeticionDTO;
import com.softlond.store.repositorio.entidades.VentaDAO;
import com.softlond.store.dominio.excepciones.ClienteNoExistenteException;
import com.softlond.store.dominio.excepciones.ProductoNoExistenteException;
import com.softlond.store.dominio.servicios.ServicioVenta;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ventas")
public class ControladorVenta {
    private final ServicioVenta servicioVenta;

    public ControladorVenta(ServicioVenta servicioVenta) {
        this.servicioVenta = servicioVenta;
    }

    @GetMapping("/")
    public List<VentaDAO> mostrarVentas() {
        return servicioVenta.mostrarVentas();
    }

    @GetMapping("/{id}")
    public List<VentaConsultaDTO> mostrarVentasPorCliente(@PathVariable("id") Long id) {
        return servicioVenta.mostrarVentasPorCliente(id);
    }

    @GetMapping
    public List<VentaConsultaDTO> mostrarVentasPorFecha(@RequestParam("fecha") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fecha) {
        return servicioVenta.mostrarVentasPorFecha(fecha);
    }

    @GetMapping("/fechaCliente")
    public List<VentaConsultaDTO> mostrarVentasPorRangoDeFechaYCliente(@RequestParam("idCliente") int id,
                                                                       @RequestParam("fechaInicio")  @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaInicio,
                                                                       @RequestParam("fechaFin")  @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaFin) {
        return servicioVenta.obtenerVentasPorRangoDeFechaYCliente(fechaInicio,fechaFin, id);
    }

    @PostMapping
    public String crearVenta(@RequestBody VentaPeticionDTO venta) {
        try {
            this.servicioVenta.crearVenta(venta);
            return "La venta fue creada exitosamente";
        } catch (ClienteNoExistenteException | ProductoNoExistenteException e) {
            return e.getMessage();
        }
    }

    @PutMapping
    public void actualizarVenta(@RequestBody VentaDAO ventaDAO) {
        this.servicioVenta.actualizarVenta(ventaDAO);
    }

    @DeleteMapping(path = "{idVenta}")
    public void eliminarVenta(@PathVariable("idVenta") Long id) {
        this.servicioVenta.eliminarVenta(id);
    }

}
