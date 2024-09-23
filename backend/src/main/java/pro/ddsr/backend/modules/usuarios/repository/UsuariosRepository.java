
package pro.ddsr.backend.modules.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.usuarios.entity.Usuarios;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByUsername(String username);
    // Define repository methods here
}
