package edu.comillas.icai.gitt.pat.spring.practica2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotNull(message = "El id de usuario es obligatorio")
    @Column(nullable = false)
    public Integer idUsuario;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ser un formato de correo válido")
    @Column(nullable = false, unique = true)
    public String correoUsuario;

    @NotNull
    @PositiveOrZero(message = "El precio no puede ser negativo")
    @Column(nullable = false)
    public Double totalPrecio = 0.0;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LineaCarrito> lineas = new ArrayList<>();

    public Carrito() {}

    public void calcularTotal() {
        if (lineas != null) {
            this.totalPrecio = lineas.stream()
                    .mapToDouble(linea -> linea.getCosteLinea())
                    .sum();
        }
    }
}