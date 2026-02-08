package edu.comillas.icai.gitt.pat.spring.practica2.controller;

import edu.comillas.icai.gitt.pat.spring.practica2.model.Carrito;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
POST - /api/carrito (Crear carrito)
GET - /api/carrito (Listado de carritos)
GET - /api/carrito/<id-carrito> (Descr. de 1 carrito con id <id-carrito>)
PUT - /api/carrito/<id-carrito> (Modificar el carrito)
DELETE - /api/carrito/<id-carrito> (Borrar el carrito)
 */
@RestController
public class CarritoController {
    private final Map<Integer, Carrito> carritos = new HashMap<>();

    @GetMapping("/api/carrito")
    public Collection<Carrito> getCarrito() {
        //Carrito test = new Carrito(1, 1, "Camiseta", 4, 40);
        //carritos.put("1", test);
        return carritos.values();
    }

    @PostMapping("/api/carrito")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito creaCarrito(@RequestBody Carrito carrito) {
        carritos.put(carrito.getIdCarrito(), carrito);
        return carrito;
    }
    //El RequestBody mapea a un fromato JSON las proppiedades de Carrito

    @GetMapping("/api/carrito/{idCarrito}")
    public Carrito getCarrito(@PathVariable int idCarrito) {
        return carritos.get(idCarrito);
    }
//    @PostMapping("/api/contadores")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ModeloContador crea(@RequestBody ModeloContador contadorNuevo) {
//        contadores.put(contadorNuevo.nombre(), contadorNuevo);
//        return contadorNuevo;
//    }
//
//    @GetMapping("/api/contadores/{nombre}")
//    public ModeloContador contador(@PathVariable String nombre) {
//        return contadores.get(nombre);
//    }
//
//    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
//    public ModeloContador incrementa(@PathVariable String nombre,
//                                     @PathVariable Integer incremento) {
//        ModeloContador contadorActual = contadores.get(nombre);
//        ModeloContador contadorIncrementado =
//                new ModeloContador(nombre, contadorActual.valor() + incremento);
//        contadores.put(nombre, contadorIncrementado);
//        return contadorIncrementado;
//    }
}

