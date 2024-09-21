
package pro.ddsr.backend.modules.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.proyecto.entity.Proyecto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Define repository methods here
}
