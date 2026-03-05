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
    private Logger logger = LoggerFactory.getLogger(getClass());
    /* logger.error("Cuando se produce un error inesperado en la lógica que hay que revisar", exception);
    logger.warn("Cuando la lógica detecta algo que hay que mantener");
    logger.info("Información importante para hacer seguimiento de la ejecución");
    logger.debug("Información que ayuda a identificar problemas");
    logger.trace("Trazas de ayuda durante el {}", "desarrollo");
     */

    //las trazas se hacen a lo largo de todo el código
    /*
    Controller	"Petición recibida en /api/carrito para el usuario X".	INFO
    Service	"Calculando total del carrito...", "Aplicando descuento...".	DEBUG / INFO
    Repository	Spring ya lo hace por ti (puedes ver las SQL en consola).	DEBUG

    Es si tenemos un manejador global de errores
    Global	"ERROR: Carrito no encontrado con ID 5".	ERROR
    los Catch de errores: Si usas un try-catch, siempre pon un log.error("Descripción del fallo", e).
     */

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
    public ResponseEntity<?> añadirLinea(@PathVariable Integer id, @RequestBody LineaCarrito linea) {
        //no s eha hecho en un manejador global de errores, pero lo hacemos aquí para ejemplificar como
        // se hace el manejo de errores y además tiene trazas para explicar el error
        try {
            Carrito actualizado = carritoService.añadirLinea(id, linea);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            log.error("Error al añadir línea: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se pudo añadir la línea. Revisa los datos.");
        }
    }

    @DeleteMapping("/{id}/lineas/{idLinea}")
    public void borrarLinea(@PathVariable Integer id, @PathVariable Integer idLinea) {
        carritoService.eliminarLinea(id, idLinea);
    }
}