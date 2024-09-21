
package pro.ddsr.backend.modules.actividad_usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.actividad_usuario.repository.ActividadUsuarioRepository;
import pro.ddsr.backend.modules.actividad_usuario.entity.ActividadUsuario;

@Service
public class ActividadUsuarioService {
    @Autowired
    ActividadUsuarioRepository actividad_usuarioRepository;

    @Transactional
    public Optional<ActividadUsuario> delete(Long id) {
        Optional<ActividadUsuario> optionalActividadUsuario = this.actividad_usuarioRepository.findById(id);
        optionalActividadUsuario.ifPresent(
            ActividadUsuarioFound -> {
                this.actividad_usuarioRepository.delete(ActividadUsuarioFound);
            }
        );
        return optionalActividadUsuario;
    }
 
    public List<ActividadUsuario> findAll() {
        return (List<ActividadUsuario>) this.actividad_usuarioRepository.findAll();
    }

    public Optional<ActividadUsuario> findById(Long id) {
        return this.actividad_usuarioRepository.findById(id);
    }

    public ActividadUsuario save(ActividadUsuario ActividadUsuario) {
        return this.actividad_usuarioRepository.save(ActividadUsuario);
    }

    public Optional<ActividadUsuario> update(Long id, ActividadUsuario actividad_usuario) {
        Optional<ActividadUsuario> optionalActividadUsuario = this.actividad_usuarioRepository.findById(id);
        if (optionalActividadUsuario.isPresent()) {
            ActividadUsuario actividad_usuarioItem = optionalActividadUsuario.orElseThrow();
            // Sets
            return Optional.of(this.actividad_usuarioRepository.save(actividad_usuarioItem));
        }
        return optionalActividadUsuario;
    }
}
