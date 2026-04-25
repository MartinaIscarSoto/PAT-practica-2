package edu.comillas.icai.gitt.pat.spring.practica2.service;

import edu.comillas.icai.gitt.pat.spring.practica2.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.practica2.entity.LineaCarrito;
import edu.comillas.icai.gitt.pat.spring.practica2.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> obtenerTodos() {
        // Forzamos la conversión de Iterable a List
        return (List<Carrito>) carritoRepository.findAll();
    }
    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito añadirLinea(Integer idCarrito, LineaCarrito linea) {
        // 1. Buscamos el carrito (o lanzamos error si no existe)
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        // 2. IMPORTANTE: Como quitamos el @PrePersist, calculamos el coste de la línea AQUÍ
        linea.costeLinea = linea.precioUnitario * linea.unidades;
        // 3. Establecemos la relación bidireccional
        linea.carrito = carrito;      // La línea sabe a qué carrito pertenece
        carrito.lineas.add(linea);    // El carrito añade la línea a su lista
        // 4. Recalculamos el total del carrito con la nueva línea
        carrito.calcularTotal();
        // 5. Guardamos. Al tener CascadeType.ALL, se guarda el carrito y la línea a la vez
        return carritoRepository.save(carrito);
    }

    public void eliminarLinea(Integer idCarrito, Integer idLinea) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        carrito.getLineas().removeIf(l -> l.getId().equals(idLinea));
        carrito.calcularTotal();
        carritoRepository.save(carrito);
    }
}