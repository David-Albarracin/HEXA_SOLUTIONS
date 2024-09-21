
package pro.ddsr.backend.modules.actividad_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.actividad_usuario.entity.ActividadUsuario;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadUsuarioRepository extends JpaRepository<ActividadUsuario, Long> {
    // Define repository methods here
}
