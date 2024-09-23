
package pro.ddsr.backend.modules.actividad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.actividad.repository.ActividadRepository;
import pro.ddsr.backend.modules.actividad.entity.Actividad;

@Service
public class ActividadService {
    @Autowired
    ActividadRepository actividadRepository;

    @Transactional
    public Optional<Actividad> delete(Long id) {
        Optional<Actividad> optionalActividad = this.actividadRepository.findById(id);
        optionalActividad.ifPresent(
            ActividadFound -> {
                this.actividadRepository.delete(ActividadFound);
            }
        );
        return optionalActividad;
    }
 
    public List<Actividad> findAll() {
        return (List<Actividad>) this.actividadRepository.findAll();
    }

    public Optional<Actividad> findById(Long id) {
        return this.actividadRepository.findById(id);
    }

    public Actividad save(Actividad Actividad) {
        return this.actividadRepository.save(Actividad);
    }

    public Optional<Actividad> update(Long id, Actividad actividad) {
        Optional<Actividad> optionalActividad = this.actividadRepository.findById(id);
        if (optionalActividad.isPresent()) {
            Actividad actividadItem = optionalActividad.orElseThrow();
            actividadItem.setEstado(actividad.getEstado());
            actividadItem.setNombre(actividad.getNombre());
            actividadItem.setProyecto(actividad.getProyecto());
            return Optional.of(this.actividadRepository.save(actividadItem));
        }
        return optionalActividad;
    }
}
