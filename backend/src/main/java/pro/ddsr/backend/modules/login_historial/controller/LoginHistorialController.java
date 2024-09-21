
package pro.ddsr.backend.modules.login_historial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.ddsr.backend.modules.login_historial.service.LoginHistorialService;
import pro.ddsr.backend.modules.login_historial.entity.LoginHistorial;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login_historial")
public class LoginHistorialController {

    @Autowired
    private LoginHistorialService login_historialService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<LoginHistorial> listLoginHistorial() {
        return this.login_historialService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<LoginHistorial> view(@PathVariable Long id) {
        Optional<LoginHistorial> optionalLoginHistorial = login_historialService.findById(id);
        if (optionalLoginHistorial.isPresent()) {
            return ResponseEntity.ok(optionalLoginHistorial.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody LoginHistorial login_historial, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(login_historialService.save(login_historial));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<LoginHistorial> update(@PathVariable Long id, @Valid @RequestBody LoginHistorial login_historial) {
        Optional<LoginHistorial> login_historialOptional = this.login_historialService.update(id, login_historial);
        if (login_historialOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(login_historialOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<LoginHistorial> delete(@PathVariable Long id) {
        Optional<LoginHistorial> optionalLoginHistorial = this.login_historialService.delete(id);
        if (optionalLoginHistorial.isPresent()) {
            return ResponseEntity.ok(optionalLoginHistorial.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
