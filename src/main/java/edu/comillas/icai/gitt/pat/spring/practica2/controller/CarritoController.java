package edu.comillas.icai.gitt.pat.spring.practica2.controller;

import edu.comillas.icai.gitt.pat.spring.practica2.model.Carrito;
import edu.comillas.icai.gitt.pat.spring.practica2.model.LineaCarrito;
import edu.comillas.icai.gitt.pat.spring.practica2.service.CarritoService;
import jakarta.validation.Valid; // ¡Importante!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> getAll() {
        return carritoService.obtenerTodos();
    }
    @PostMapping
    public Carrito crear(@Valid @RequestBody Carrito carrito) {
        // @Valid obliga a que el JSON cumpla las reglas de la Entity
        return carritoService.crearCarrito(carrito);
    }

    @PostMapping("/{id}/lineas")
    public Carrito añadirLinea(@PathVariable Integer id, @Valid @RequestBody LineaCarrito linea) {
        // También validamos la línea antes de añadirla
        return carritoService.añadirLinea(id, linea);
    }

    @DeleteMapping("/{id}/lineas/{idLinea}")
    public void borrarLinea(@PathVariable Integer id, @PathVariable Integer idLinea) {
        carritoService.eliminarLinea(id, idLinea);
    }
}