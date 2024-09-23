
package pro.ddsr.backend.modules.estados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.estados.repository.EstadosRepository;
import pro.ddsr.backend.modules.estados.entity.Estados;

@Service
public class EstadosService {
    @Autowired
    EstadosRepository estadosRepository;

    @Transactional
    public Optional<Estados> delete(Long id) {
        Optional<Estados> optionalEstados = this.estadosRepository.findById(id);
        optionalEstados.ifPresent(
            EstadosFound -> {
                this.estadosRepository.delete(EstadosFound);
            }
        );
        return optionalEstados;
    }
 
    public List<Estados> findAll() {
        return (List<Estados>) this.estadosRepository.findAll();
    }

    public Optional<Estados> findById(Long id) {
        return this.estadosRepository.findById(id);
    }

    public Estados save(Estados Estados) {
        return this.estadosRepository.save(Estados);
    }

    public Optional<Estados> update(Long id, Estados estados) {
        Optional<Estados> optionalEstados = this.estadosRepository.findById(id);
        if (optionalEstados.isPresent()) {
            Estados estadosItem = optionalEstados.orElseThrow();
            estadosItem.setActividades(estados.getActividades());
            estadosItem.setNombre(estados.getNombre());
            return Optional.of(this.estadosRepository.save(estadosItem));
        }
        return optionalEstados;
    }
}
