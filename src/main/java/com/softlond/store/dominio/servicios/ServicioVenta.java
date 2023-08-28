package com.softlond.store.dominio.servicios;


import com.softlond.store.dominio.dto.ProductoVentaPeticionDTO;
import com.softlond.store.dominio.dto.VentaConsultaDTO;
import com.softlond.store.dominio.dto.VentaPeticionDTO;
import com.softlond.store.repositorio.entidades.ProductoVentaDAO;
import com.softlond.store.repositorio.entidades.ClienteDAO;
import com.softlond.store.repositorio.entidades.ProductoDAO;
import com.softlond.store.repositorio.entidades.VentaDAO;
import com.softlond.store.dominio.excepciones.ClienteNoExistenteException;
import com.softlond.store.dominio.excepciones.ProductoNoExistenteException;
import com.softlond.store.repositorio.RepositorioCliente;
import com.softlond.store.repositorio.RepositorioProducto;
import com.softlond.store.repositorio.RepositorioVenta;
import com.softlond.store.repositorio.mappers.VentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioVenta {
    private final RepositorioVenta repositorioVenta;
    private final RepositorioProducto repositorioProducto;
    private final RepositorioCliente repositorioCliente;
    private final ServicioVentasProducto servicioVentasProducto;
    private final VentaMapper ventaMapper;

    @Autowired
    public ServicioVenta(RepositorioVenta repositorioVenta, RepositorioProducto repositorioProducto, RepositorioCliente repositorioCliente, ServicioVentasProducto servicioVentasProducto) {
        this.repositorioVenta = repositorioVenta;
        this.repositorioProducto = repositorioProducto;
        this.repositorioCliente = repositorioCliente;
        this.servicioVentasProducto = servicioVentasProducto;
        this.ventaMapper = new VentaMapper();
    }

    public List<VentaDAO> mostrarVentas() {
        return (List<VentaDAO>) repositorioVenta.findAll();
    }

    public List<VentaConsultaDTO> mostrarVentasPorCliente(Long idCliente) {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta.obtenerVentasPorCliente(idCliente));
    }

    public List<VentaConsultaDTO> mostrarVentasPorFecha(Date fecha) {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta.obtenerVentasPorFecha(fecha));
    }

    public List<VentaConsultaDTO> obtenerVentasPorRangoDeFechaYCliente(Date fechaInicio, Date fechaFin, int idCliente) {
        return ventaMapper.transformarListaDeVentasADTOParaConsulta(repositorioVenta.obtenerVentasPorRangoDeFechaYCliente(fechaInicio,fechaFin, idCliente));
    }

    @Transactional(rollbackFor = Exception.class)
    public void crearVenta(VentaPeticionDTO ventaPeticionDTO) throws ClienteNoExistenteException, ProductoNoExistenteException {
        ClienteDAO clienteDAO = obtenerCliente(ventaPeticionDTO);
        Map<Long, ProductoDAO> productos = obtenerProductos(ventaPeticionDTO);
        VentaDAO ventaDAO = new VentaDAO();
        ventaDAO.setCliente(clienteDAO);
        ventaDAO.setFecha(ventaPeticionDTO.getFecha());
        VentaDAO ventaDAOGuardada = this.repositorioVenta.save(ventaDAO);
        guardarProductosEnLaVenta(productos, ventaDAOGuardada);
    }

    @Transactional(rollbackFor = Exception.class) // Método para guardar productos transaccionalmente
    private void guardarProductosEnLaVenta(Map<Long, ProductoDAO> productos, VentaDAO ventaDAOGuardada) throws ProductoNoExistenteException {
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
        // Verificar existencia de los productos
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

    private ClienteDAO obtenerCliente(VentaPeticionDTO ventaPeticionDTO) throws ClienteNoExistenteException {
        Optional<ClienteDAO> cliente = repositorioCliente.findById(ventaPeticionDTO.getCedulaCliente());
        if (cliente.isEmpty()) {
            throw new ClienteNoExistenteException();
        } else {
            return cliente.get();
        }
    }

    public void actualizarVenta(VentaDAO ventaDAO) {
        this.repositorioVenta.save(ventaDAO);
    }

    public void eliminarVenta(Long idVenta) {
        this.repositorioVenta.deleteById(idVenta);
    }
}
