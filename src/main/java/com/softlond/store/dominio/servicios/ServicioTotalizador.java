package com.softlond.store.dominio.servicios;

import com.softlond.store.dominio.dto.VentaConsultaDTO;
import com.softlond.store.dominio.dto.VentaPeticionDTO;
import com.softlond.store.dominio.excepciones.DescuentoNoExistenteException;
import com.softlond.store.repositorio.entidades.ProductoDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ServicioTotalizador {
    private final ServicioDescuento servicioDescuento;

    public ServicioTotalizador(ServicioDescuento servicioDescuento) {
        this.servicioDescuento = servicioDescuento;
    }


    public double calcularTotal(Map<Long, ProductoDAO> productos, VentaPeticionDTO ventaPeticionDTO,List<VentaConsultaDTO> ventasDeLosUltimos30Dias) throws DescuentoNoExistenteException {
        double porcentajeDescuento = servicioDescuento
                .obtenerDescuentoDeCliente(calcularTotalEnVentasLosUltimos30DiasDelCliente(ventaPeticionDTO,ventasDeLosUltimos30Dias));
        double totalCompraActual = calcularTotalDeCompraActual(productos);
        if (porcentajeDescuento > 0) {
            return totalCompraActual - totalCompraActual * porcentajeDescuento;
        } else {
            return totalCompraActual;
        }
    }

    private double calcularTotalDeCompraActual(Map<Long, ProductoDAO> productos) {
        return productos
                .entrySet()
                .stream()
                .map(productoVenta -> productoVenta.getKey() * productoVenta.getValue().getPrecio())
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
