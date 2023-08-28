package com.softlond.store.controlador;

import com.softlond.store.dominio.dto.ProductoConsultaDTO;
import com.softlond.store.dominio.dto.ProductoPeticionDTO;
import com.softlond.store.dominio.excepciones.ProductoNoExistenteException;
import com.softlond.store.dominio.excepciones.CategoriaNoExistenteException;
import com.softlond.store.dominio.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/productos")
public class ControladorProducto {
    private final ServicioProducto servicioProducto;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @GetMapping
    public List<ProductoConsultaDTO> mostrarProductos() {
        return this.servicioProducto.mostrarProductos();
    }

    @PostMapping
    public String crearProducto(@RequestBody ProductoPeticionDTO producto) {
        try {
            servicioProducto.crearProducto(producto);
            return "Producto creado exitosamente.";
        } catch (CategoriaNoExistenteException e) {
            return e.getMessage();
        }
    }

    @PutMapping
    public String actualizarProducto(@RequestBody ProductoPeticionDTO productoDTO) {
        try {
            this.servicioProducto.actualizarProducto(productoDTO);
            return "Producto actualizado exitosamente.";
        } catch (ProductoNoExistenteException | CategoriaNoExistenteException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping(path = "{id}")
    public void eliminarProducto(@PathVariable("id") Long id) {
        this.servicioProducto.eliminarProducto(id);
    }
}
