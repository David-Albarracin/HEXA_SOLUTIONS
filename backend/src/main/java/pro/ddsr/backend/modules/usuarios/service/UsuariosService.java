
package pro.ddsr.backend.modules.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.usuarios.repository.UsuariosRepository;
import pro.ddsr.backend.modules.usuarios.entity.Usuarios;

@Service
public class UsuariosService {
    @Autowired
    UsuariosRepository usuariosRepository;

    @Transactional
    public Optional<Usuarios> delete(Long id) {
        Optional<Usuarios> optionalUsuarios = this.usuariosRepository.findById(id);
        optionalUsuarios.ifPresent(
            UsuariosFound -> {
                this.usuariosRepository.delete(UsuariosFound);
            }
        );
        return optionalUsuarios;
    }
 
    public List<Usuarios> findAll() {
        return (List<Usuarios>) this.usuariosRepository.findAll();
    }

    public Optional<Usuarios> findById(Long id) {
        return this.usuariosRepository.findById(id);
    }

    public Usuarios save(Usuarios Usuarios) {
        return this.usuariosRepository.save(Usuarios);
    }

    public Optional<Usuarios> update(Long id, Usuarios usuarios) {
        Optional<Usuarios> optionalUsuarios = this.usuariosRepository.findById(id);
        if (optionalUsuarios.isPresent()) {
            Usuarios usuariosItem = optionalUsuarios.orElseThrow();
            usuariosItem.setNombre(usuarios.getNombre());
            usuariosItem.setEmail(usuarios.getEmail());
            usuariosItem.setPassword(usuarios.getPassword());
            usuariosItem.setRol(usuarios.getRol());
            return Optional.of(this.usuariosRepository.save(usuariosItem));
        }
        return optionalUsuarios;
    }
}
