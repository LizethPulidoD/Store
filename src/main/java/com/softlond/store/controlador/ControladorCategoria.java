package com.softlond.store.controlador;

import com.softlond.store.dominio.dto.comandos.CategoriaActualizarDTO;
import com.softlond.store.dominio.dto.comandos.CategoriaConsultaDTO;
import com.softlond.store.dominio.dto.comandos.CategoriaDTO;
import com.softlond.store.dominio.excepciones.CategoriaNoExistenteException;
import com.softlond.store.dominio.servicios.ServicioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categorias")
public class ControladorCategoria {
    private final ServicioCategoria servicioCategoria;

    @Autowired
    public ControladorCategoria(ServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    @GetMapping
    public List<CategoriaDTO> mostrarCategorias() {
        return this.servicioCategoria.mostrarCategorias();
    }

    @PostMapping
    public void crearCategoria(@RequestBody CategoriaConsultaDTO categoriaDTO) {
        this.servicioCategoria.crearCategoria(categoriaDTO);
    }

    @PutMapping
    public String actualizarCategoria(@RequestBody CategoriaActualizarDTO categoriaDTO) {
        try {
            this.servicioCategoria.actualizarCategoria(categoriaDTO);
            return "Categoria actualizada exitosamente.";
        } catch (CategoriaNoExistenteException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping(path = "{idCategoria}")
    public void eliminarCategoria(@PathVariable("idCategoria") Long id) {
        this.servicioCategoria.eliminarCategoria(id);
    }
}
