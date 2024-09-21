
package pro.ddsr.backend.modules.grupos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.grupos.repository.GruposRepository;
import pro.ddsr.backend.modules.grupos.entity.Grupos;

@Service
public class GruposService {
    @Autowired
    GruposRepository gruposRepository;

    @Transactional
    public Optional<Grupos> delete(Long id) {
        Optional<Grupos> optionalGrupos = this.gruposRepository.findById(id);
        optionalGrupos.ifPresent(
            GruposFound -> {
                this.gruposRepository.delete(GruposFound);
            }
        );
        return optionalGrupos;
    }
 
    public List<Grupos> findAll() {
        return (List<Grupos>) this.gruposRepository.findAll();
    }

    public Optional<Grupos> findById(Long id) {
        return this.gruposRepository.findById(id);
    }

    public Grupos save(Grupos Grupos) {
        return this.gruposRepository.save(Grupos);
    }

    public Optional<Grupos> update(Long id, Grupos grupos) {
        Optional<Grupos> optionalGrupos = this.gruposRepository.findById(id);
        if (optionalGrupos.isPresent()) {
            Grupos gruposItem = optionalGrupos.orElseThrow();
            // Sets
            return Optional.of(this.gruposRepository.save(gruposItem));
        }
        return optionalGrupos;
    }
}
