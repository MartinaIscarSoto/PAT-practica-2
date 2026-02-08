package edu.comillas.icai.gitt.pat.spring.practica2.controller;

import edu.comillas.icai.gitt.pat.spring.practica2.model.Carrito;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CarritoController {
    private final Map<Integer, Carrito> carritos = new HashMap<>(Map.of(
            1, new Carrito(1,1, "Camiseta", 4, 200),
             2, new Carrito(2,3,"Pijama", 1, 15)));

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
        if(!carritos.containsKey(idCarrito)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El carrito no existe, prueba buscar con otro idCarrito");
        }
        return carritos.get(idCarrito);
    }

    @DeleteMapping("/api/carrito/{idCarrito}")
    public void borrarCarrito(@PathVariable int idCarrito) {
        carritos.remove(idCarrito);
    }

    @PutMapping("/api/carrito/{idCarrito}")
    public Carrito modificarCarrito(@PathVariable int idCarrito, @RequestBody Carrito carrito){
         carrito.setIdCarrito(idCarrito);
         carritos.put( idCarrito, carrito);
        return carrito;
    }

}

