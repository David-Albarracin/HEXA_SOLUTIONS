
package pro.ddsr.backend.modules.roles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.roles.repository.RolesRepository;
import pro.ddsr.backend.modules.roles.entity.Roles;

@Service
public class RolesService {
    @Autowired
    RolesRepository rolesRepository;

    @Transactional
    public Optional<Roles> delete(Long id) {
        Optional<Roles> optionalRoles = this.rolesRepository.findById(id);
        optionalRoles.ifPresent(
            RolesFound -> {
                this.rolesRepository.delete(RolesFound);
            }
        );
        return optionalRoles;
    }
 
    public List<Roles> findAll() {
        return (List<Roles>) this.rolesRepository.findAll();
    }

    public Optional<Roles> findById(Long id) {
        return this.rolesRepository.findById(id);
    }

    public Roles save(Roles Roles) {
        return this.rolesRepository.save(Roles);
    }

    public Optional<Roles> update(Long id, Roles roles) {
        Optional<Roles> optionalRoles = this.rolesRepository.findById(id);
        if (optionalRoles.isPresent()) {
            Roles rolesItem = optionalRoles.orElseThrow();
            // Sets
            return Optional.of(this.rolesRepository.save(rolesItem));
        }
        return optionalRoles;
    }
}
