import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false) // Una línea siempre debe pertenecer a un carrito
    @JsonIgnore
    public Carrito carrito;

    public LineaCarrito() {}
}