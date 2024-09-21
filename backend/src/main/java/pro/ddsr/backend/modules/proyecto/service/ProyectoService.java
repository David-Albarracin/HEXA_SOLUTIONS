
package pro.ddsr.backend.modules.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.proyecto.repository.ProyectoRepository;
import pro.ddsr.backend.modules.proyecto.entity.Proyecto;

@Service
public class ProyectoService {
    @Autowired
    ProyectoRepository proyectoRepository;

    @Transactional
    public Optional<Proyecto> delete(Long id) {
        Optional<Proyecto> optionalProyecto = this.proyectoRepository.findById(id);
        optionalProyecto.ifPresent(
            ProyectoFound -> {
                this.proyectoRepository.delete(ProyectoFound);
            }
        );
        return optionalProyecto;
    }
 
    public List<Proyecto> findAll() {
        return (List<Proyecto>) this.proyectoRepository.findAll();
    }

    public Optional<Proyecto> findById(Long id) {
        return this.proyectoRepository.findById(id);
    }

    public Proyecto save(Proyecto Proyecto) {
        return this.proyectoRepository.save(Proyecto);
    }

    public Optional<Proyecto> update(Long id, Proyecto proyecto) {
        Optional<Proyecto> optionalProyecto = this.proyectoRepository.findById(id);
        if (optionalProyecto.isPresent()) {
            Proyecto proyectoItem = optionalProyecto.orElseThrow();
            // Sets
            return Optional.of(this.proyectoRepository.save(proyectoItem));
        }
        return optionalProyecto;
    }
}
