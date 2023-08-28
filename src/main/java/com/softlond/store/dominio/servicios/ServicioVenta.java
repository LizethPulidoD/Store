package com.softlond.store.dominio.servicios;


import com.softlond.store.dominio.dto.ProductoVentaPeticionDTO;
import com.softlond.store.dominio.dto.VentaConsultaDTO;
import com.softlond.store.dominio.dto.VentaPeticionDTO;
import com.softlond.store.dominio.excepciones.ClienteNoExistenteException;
import com.softlond.store.dominio.excepciones.DescuentoNoExistenteException;
import com.softlond.store.dominio.excepciones.ProductoNoExistenteException;
import com.softlond.store.repositorio.RepositorioProducto;
import com.softlond.store.repositorio.RepositorioVenta;
import com.softlond.store.repositorio.entidades.ClienteDAO;
import com.softlond.store.repositorio.entidades.ProductoDAO;
import com.softlond.store.repositorio.entidades.ProductoVentaDAO;
import com.softlond.store.repositorio.entidades.VentaDAO;
import com.softlond.store.repositorio.mappers.ClienteMapper;
import com.softlond.store.repositorio.mappers.VentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioVenta {
    private final RepositorioVenta repositorioVenta;
    private final RepositorioProducto repositorioProducto;
    private final ServicioCliente servicioCliente;
    private final ServicioVentasProducto servicioVentasProducto;
    private final ServicioTotalizador servicioTotalizador;
    private final VentaMapper ventaMapper;
    private final ClienteMapper clienteMapper;


    @Autowired
    public ServicioVenta(RepositorioVenta repositorioVenta, RepositorioProducto repositorioProducto, ServicioCliente servicioCliente, ServicioVentasProducto servicioVentasProducto, ServicioTotalizador servicioTotalizador) {
        this.repositorioVenta = repositorioVenta;
        this.repositorioProducto = repositorioProducto;
        this.servicioCliente = servicioCliente;
        this.servicioVentasProducto = servicioVentasProducto;
        this.servicioTotalizador = servicioTotalizador;
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
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta.obtenerVentasPorFecha(Date.valueOf(fecha)));
    }

    public List<VentaConsultaDTO> obtenerVentasPorRangoDeFechaYCliente(LocalDate fechaInicio, LocalDate fechaFin, int idCliente) {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta
                .obtenerVentasPorRangoDeFechaYCliente(Date.valueOf(fechaInicio), Date.valueOf(fechaFin), idCliente));
    }

    private List<VentaConsultaDTO> obtenerVentasDeLosUltimos30Dias(VentaPeticionDTO ventaPeticionDTO) {
        return obtenerVentasPorRangoDeFechaYCliente(
                ventaPeticionDTO.getFecha().minusMonths(1),
                ventaPeticionDTO.getFecha(),
                ventaPeticionDTO.getCedulaCliente());
    }


    @Transactional(rollbackFor = Exception.class)
    public void crearVenta(VentaPeticionDTO ventaPeticionDTO) throws ClienteNoExistenteException, ProductoNoExistenteException, DescuentoNoExistenteException {
        ClienteDAO clienteDAO = clienteMapper.transformarADAO(servicioCliente.consultarClientePorCedula(ventaPeticionDTO.getCedulaCliente()));
        Map<Long, ProductoDAO> productos = obtenerProductos(ventaPeticionDTO);
        VentaDAO ventaDAO = this.repositorioVenta.save(new VentaDAO(clienteDAO,ventaPeticionDTO.getFecha()));
        guardarProductosEnLaVenta(productos, ventaDAO);
        actualizarTotal(ventaPeticionDTO, productos, ventaDAO);
    }

    private void actualizarTotal(VentaPeticionDTO ventaPeticionDTO, Map<Long, ProductoDAO> productos, VentaDAO ventaDAOGuardada) throws DescuentoNoExistenteException {
        double total = servicioTotalizador.calcularTotal(productos, ventaPeticionDTO, obtenerVentasDeLosUltimos30Dias(ventaPeticionDTO));
        ventaDAOGuardada.setTotal(total);
        this.repositorioVenta.save(ventaDAOGuardada);
    }

    @Transactional(rollbackFor = Exception.class)
    private void guardarProductosEnLaVenta(Map<Long, ProductoDAO> productos, VentaDAO ventaDAOGuardada) {
        servicioVentasProducto.guardarVentasProducto(productos
                .entrySet()
                .stream()
                .map(productoEntry -> {
                    ProductoVentaDAO productoVentaDAO = new ProductoVentaDAO();
                    productoVentaDAO.setProducto(productoEntry.getValue());
                    productoVentaDAO.setCantidad(productoEntry.getKey());
                    productoVentaDAO.setVenta(ventaDAOGuardada);
                    return productoVentaDAO;
                }).collect(Collectors.toList()));

    }

    private Map<Long, ProductoDAO> obtenerProductos(VentaPeticionDTO ventaPeticionDTO) throws ProductoNoExistenteException {
        Map<Long, ProductoDAO> listadoDeProductos = new HashMap<>();
        for (ProductoVentaPeticionDTO productoDTO : ventaPeticionDTO.getProductos()) {
            Optional<ProductoDAO> producto = repositorioProducto.findById(productoDTO.getIdProducto());
            if (producto.isPresent()) {
                listadoDeProductos.put(productoDTO.getCantidad(), producto.get());
            } else {
                throw new ProductoNoExistenteException(productoDTO.getIdProducto());
            }
        }
        return listadoDeProductos;
    }

    public void eliminarVenta(Long idVenta) {
        this.repositorioVenta.deleteById(idVenta);
    }
}
