package com.softlond.store.dominio.servicios;


import com.softlond.store.dominio.dto.ProductoVentaPeticionDTO;
import com.softlond.store.dominio.dto.VentaConsultaDTO;
import com.softlond.store.dominio.dto.VentaPeticionDTO;
import com.softlond.store.dominio.excepciones.ClienteNoExistenteException;
import com.softlond.store.dominio.excepciones.DescuentoNoExistenteException;
import com.softlond.store.dominio.excepciones.ProductoNoExistenteException;
import com.softlond.store.repositorio.RepositorioVenta;
import com.softlond.store.repositorio.entidades.ClienteDAO;
import com.softlond.store.repositorio.entidades.ProductoDAO;
import com.softlond.store.repositorio.entidades.ProductoVentaDAO;
import com.softlond.store.repositorio.entidades.VentaDAO;
import com.softlond.store.repositorio.mappers.ClienteMapper;
import com.softlond.store.repositorio.mappers.ProductoMapper;
import com.softlond.store.repositorio.mappers.VentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioVenta {
    private final RepositorioVenta repositorioVenta;
    private final ServicioProducto servicioProducto;
    private final ServicioCliente servicioCliente;
    private final ServicioVentasProducto servicioVentasProducto;
    private final ServicioTotalizador servicioTotalizador;
    private final VentaMapper ventaMapper;
    private final ClienteMapper clienteMapper;
    private final ProductoMapper productoMapper;


    @Autowired
    public ServicioVenta(RepositorioVenta repositorioVenta, ServicioProducto servicioProducto, ServicioCliente servicioCliente, ServicioVentasProducto servicioVentasProducto, ServicioTotalizador servicioTotalizador) {
        this.repositorioVenta = repositorioVenta;
        this.servicioProducto = servicioProducto;
        this.servicioCliente = servicioCliente;
        this.servicioVentasProducto = servicioVentasProducto;
        this.servicioTotalizador = servicioTotalizador;
        this.productoMapper = new ProductoMapper();
        this.ventaMapper = new VentaMapper();
        this.clienteMapper = new ClienteMapper();
    }

    public List<VentaConsultaDTO> mostrarVentas() {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta((List<VentaDAO>) repositorioVenta.findAll());
    }

    public List<VentaConsultaDTO> mostrarVentasPorCliente(Long idCliente) {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta.obtenerVentasPorCliente(idCliente));
    }

    public List<VentaConsultaDTO> mostrarVentasPorFecha(LocalDate fecha) {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta.obtenerVentasPorFecha(convertirLocalDateADate(fecha)));
    }

    public List<VentaConsultaDTO> obtenerVentasPorRangoDeFechaYCliente(LocalDate fechaInicio, LocalDate fechaFin, int idCliente) {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta
                .obtenerVentasPorRangoDeFechaYCliente(convertirLocalDateADate(fechaInicio),convertirLocalDateADate(fechaFin), idCliente));
    }


    @Transactional(rollbackFor = Exception.class)
    public void crearVenta(VentaPeticionDTO ventaPeticionDTO) throws ClienteNoExistenteException, ProductoNoExistenteException, DescuentoNoExistenteException {
        ClienteDAO clienteDAO = clienteMapper.transformarADAO(servicioCliente.consultarClientePorCedula(ventaPeticionDTO.getCedulaCliente()));
        Map<ProductoDAO, Long> productos = obtenerProductos(ventaPeticionDTO);
        VentaDAO ventaDAO = this.repositorioVenta.save(new VentaDAO(clienteDAO,convertirLocalDateADate(ventaPeticionDTO.getFecha())));
        guardarProductosEnLaVenta(productos, ventaDAO);
        actualizarTotal(ventaPeticionDTO, productos, ventaDAO);
    }
    public void eliminarVenta(Long idVenta) {
        this.repositorioVenta.deleteById(idVenta);
    }

    private void actualizarTotal(VentaPeticionDTO ventaPeticionDTO, Map<ProductoDAO,Long > productos, VentaDAO ventaDAOGuardada) throws DescuentoNoExistenteException {
        double total = servicioTotalizador.calcularTotal(productos, ventaPeticionDTO, obtenerVentasDeLosUltimos30Dias(ventaPeticionDTO));
        ventaDAOGuardada.setTotal(total);
        this.repositorioVenta.save(ventaDAOGuardada);
    }

    @Transactional(rollbackFor = Exception.class)
    private void guardarProductosEnLaVenta(Map<ProductoDAO,Long > productos, VentaDAO ventaDAOGuardada) {
        servicioVentasProducto.guardarVentasProducto(productos
                .entrySet()
                .stream()
                .map(productoEntry -> {
                    ProductoVentaDAO productoVentaDAO = new ProductoVentaDAO();
                    productoVentaDAO.setProducto(productoEntry.getKey());
                    productoVentaDAO.setCantidad(productoEntry.getValue());
                    productoVentaDAO.setVenta(ventaDAOGuardada);
                    return productoVentaDAO;
                }).collect(Collectors.toList()));
    }

    private Map<ProductoDAO, Long> obtenerProductos(VentaPeticionDTO ventaPeticionDTO) throws ProductoNoExistenteException {
        Map<ProductoDAO, Long> listadoDeProductos = new HashMap<>();
        for (ProductoVentaPeticionDTO productoDTO : ventaPeticionDTO.getProductos()) {
            ProductoDAO producto = productoMapper.transformarADAO(servicioProducto.consultarPorID(productoDTO.getIdProducto()));
            listadoDeProductos.put(producto,productoDTO.getCantidad());
        }
        return listadoDeProductos;
    }

    private List<VentaConsultaDTO> obtenerVentasDeLosUltimos30Dias(VentaPeticionDTO ventaPeticionDTO) {
        return obtenerVentasPorRangoDeFechaYCliente(
                ventaPeticionDTO.getFecha().minusMonths(1),
                ventaPeticionDTO.getFecha(),
                ventaPeticionDTO.getCedulaCliente());
    }

    public Date convertirLocalDateADate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
