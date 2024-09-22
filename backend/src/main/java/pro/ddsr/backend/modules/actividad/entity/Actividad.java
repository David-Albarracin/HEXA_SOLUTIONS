
package pro.ddsr.backend.modules.actividad.entity;


import pro.ddsr.backend.modules.proyecto.entity.Proyecto;
import pro.ddsr.backend.modules.estados.entity.Estados;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")  
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)  
    private Estados estado;

    
}
