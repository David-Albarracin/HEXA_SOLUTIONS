
package pro.ddsr.backend.modules.estados.entity;


import java.util.List;
import pro.ddsr.backend.modules.actividad.entity.Actividad;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="estados")
public class Estados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<Actividad> actividades;

   
}
