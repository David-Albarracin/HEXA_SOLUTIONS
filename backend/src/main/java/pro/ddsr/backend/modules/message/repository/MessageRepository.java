
package pro.ddsr.backend.modules.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend.modules.message.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Define repository methods here
}
