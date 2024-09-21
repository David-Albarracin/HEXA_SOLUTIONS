
package pro.ddsr.backend.modules.login_historial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.login_historial.entity.LoginHistorial;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistorialRepository extends JpaRepository<LoginHistorial, Long> {
    // Define repository methods here
}
