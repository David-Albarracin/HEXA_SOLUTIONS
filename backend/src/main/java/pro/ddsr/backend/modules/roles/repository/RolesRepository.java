
package pro.ddsr.backend.modules.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.roles.entity.Roles;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    // Define repository methods here
}
