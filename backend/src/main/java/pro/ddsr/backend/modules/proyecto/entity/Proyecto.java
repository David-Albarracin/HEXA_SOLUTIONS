package pro.ddsr.backend.modules.proyecto.entity;


import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import pro.ddsr.backend.modules.actividad.entity.Actividad;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @OneToMany(mappedBy = "proyecto")
    private List<Actividad> actividades;
}