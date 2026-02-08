package edu.comillas.icai.gitt.pat.spring.practica2.controller;

import edu.comillas.icai.gitt.pat.spring.practica2.model.Carrito;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    @DeleteMapping("/api/carrito/{idCarrito}")
    public void borrarCarrito(@PathVariable int idCarrito) {
        carritos.remove(idCarrito);
    }

    @PutMapping("/api/carrito/{idCarrito}")
    public Carrito modificarCarrito(@PathVariable int idCarrito, @RequestBody Carrito carrito){
         carritos.put(idCarrito, carrito);
         return carrito;
    }

}

