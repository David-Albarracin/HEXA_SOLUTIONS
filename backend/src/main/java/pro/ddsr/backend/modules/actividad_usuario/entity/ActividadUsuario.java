
package pro.ddsr.backend.modules.actividad_usuario.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import pro.ddsr.backend.modules.actividad.entity.Actividad;
import pro.ddsr.backend.modules.usuarios.entity.Usuarios;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="actividad_usuario")
public class ActividadUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @Column(nullable = false)
    private int tiempoInvertido;

    @Column(nullable = false)
    private LocalDate fecha;

}
