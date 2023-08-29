package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.dto.VentaConsultaDTO;
import com.softlond.store.dominio.dto.VentaPeticionDTO;
import com.softlond.store.dominio.excepciones.DescuentoNoExistenteException;
import com.softlond.store.repositorio.entidades.ProductoDAO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServicioTotalizador {
    private final ServicioDescuento servicioDescuento;
    private final ServicioJuegoCompra servicioJuegoCompra;

    public ServicioTotalizador(ServicioDescuento servicioDescuento, ServicioJuegoCompra servicioJuegoCompra) {
        this.servicioDescuento = servicioDescuento;
        this.servicioJuegoCompra = servicioJuegoCompra;
    }

    public double calcularTotal(Map< ProductoDAO,Long> productos,
                                VentaPeticionDTO ventaPeticionDTO,
                                List<VentaConsultaDTO> ventasDeLosUltimos30Dias) {
        double porcentajeDescuento = obtenerPorcentajeDescuento(ventaPeticionDTO, ventasDeLosUltimos30Dias);
        double totalCompraActual = calcularTotalDeCompraActual(productos);
        if (porcentajeDescuento > 0) {
            totalCompraActual = totalCompraActual - (totalCompraActual * porcentajeDescuento);
        }
        Optional<Float> descuentoDelJuego = servicioJuegoCompra.jugar();
        if (descuentoDelJuego.isPresent()) {
            totalCompraActual = totalCompraActual - (totalCompraActual * descuentoDelJuego.get());
        }
        return totalCompraActual;
    }

    private double obtenerPorcentajeDescuento(VentaPeticionDTO ventaPeticionDTO, List<VentaConsultaDTO> ventasDeLosUltimos30Dias) {
        double porcentajeDescuento = 0;
        try {
            porcentajeDescuento = servicioDescuento
                    .obtenerDescuentoDeCliente(calcularTotalEnVentasLosUltimos30DiasDelCliente(ventaPeticionDTO, ventasDeLosUltimos30Dias));
        } catch (DescuentoNoExistenteException e) {
            porcentajeDescuento = 0;
        }
        return porcentajeDescuento;
    }

    private double calcularTotalDeCompraActual(Map< ProductoDAO,Long> productos) {
        return productos
                .entrySet()
                .stream()
                .map(productoVenta -> productoVenta.getValue() * productoVenta.getKey().getPrecio())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double calcularTotalEnVentasLosUltimos30DiasDelCliente(VentaPeticionDTO ventaPeticionDTO,
                                                                   List<VentaConsultaDTO> ventasDeLosUltimos30Dias) {
        return ventasDeLosUltimos30Dias
                .stream()
                .map(VentaConsultaDTO::getProductos)
                .flatMap(Collection::stream)
                .map(productoVenta -> productoVenta.getPrecio() * productoVenta.getCantidad())
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
