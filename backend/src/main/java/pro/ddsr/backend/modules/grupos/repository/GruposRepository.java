
package pro.ddsr.backend.modules.grupos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.grupos.entity.Grupos;
import org.springframework.stereotype.Repository;

@Repository
public interface GruposRepository extends JpaRepository<Grupos, Long> {
    // Define repository methods here
}
