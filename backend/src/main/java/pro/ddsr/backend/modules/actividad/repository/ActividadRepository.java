
package pro.ddsr.backend.modules.actividad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.actividad.entity.Actividad;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    // Define repository methods here
}
