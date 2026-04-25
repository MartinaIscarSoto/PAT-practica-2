package edu.comillas.icai.gitt.pat.spring.practica2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class LineaCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotNull(message = "El id del artículo es obligatorio")
    @Column(nullable = false)
    public Integer idArticulo;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    @Column(nullable = false)
    public Double precioUnitario;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "Debe haber al menos una unidad")
    @Column(nullable = false)
    public Integer unidades;

    @Column(nullable = false)
    public Double costeLinea = 0.0;

    // En LineaCarrito.java (El Dueño)
    @ManyToOne
    @JoinColumn(name = "id_carrito") // "Yo soy el dueño y controlo esta columna en la base de datos"
    @JsonIgnore
    public Carrito carrito;

    public LineaCarrito() {}
}