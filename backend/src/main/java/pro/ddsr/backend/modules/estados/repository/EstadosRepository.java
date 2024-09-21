
package pro.ddsr.backend.modules.estados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.estados.entity.Estados;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosRepository extends JpaRepository<Estados, Long> {
    // Define repository methods here
}
