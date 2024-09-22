
package pro.ddsr.backend.modules.login_historial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.login_historial.repository.LoginHistorialRepository;
import pro.ddsr.backend.modules.login_historial.entity.LoginHistorial;

@Service
public class LoginHistorialService {
    @Autowired
    LoginHistorialRepository login_historialRepository;

    @Transactional
    public Optional<LoginHistorial> delete(Long id) {
        Optional<LoginHistorial> optionalLoginHistorial = this.login_historialRepository.findById(id);
        optionalLoginHistorial.ifPresent(
            LoginHistorialFound -> {
                this.login_historialRepository.delete(LoginHistorialFound);
            }
        );
        return optionalLoginHistorial;
    }
 
    public List<LoginHistorial> findAll() {
        return (List<LoginHistorial>) this.login_historialRepository.findAll();
    }

    public Optional<LoginHistorial> findById(Long id) {
        return this.login_historialRepository.findById(id);
    }

    public LoginHistorial save(LoginHistorial LoginHistorial) {
        return this.login_historialRepository.save(LoginHistorial);
    }

    public Optional<LoginHistorial> update(Long id, LoginHistorial login_historial) {
        Optional<LoginHistorial> optionalLoginHistorial = this.login_historialRepository.findById(id);
        if (optionalLoginHistorial.isPresent()) {
            LoginHistorial login_historialItem = optionalLoginHistorial.orElseThrow();
            login_historialItem.setUsuario(login_historial.getUsuario());
            login_historialItem.setFechaInicio(login_historial.getFechaInicio());
            login_historialItem.setFechaFin(login_historial.getFechaFin());
            return Optional.of(this.login_historialRepository.save(login_historialItem));
        }
        return optionalLoginHistorial;
    }
}
