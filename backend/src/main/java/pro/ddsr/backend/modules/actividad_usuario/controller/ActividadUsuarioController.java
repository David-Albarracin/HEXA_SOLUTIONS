
package pro.ddsr.backend.modules.actividad_usuario.controller;

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

import pro.ddsr.backend.modules.actividad_usuario.service.ActividadUsuarioService;
import pro.ddsr.backend.modules.actividad_usuario.entity.ActividadUsuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/actividad_usuario")
public class ActividadUsuarioController {

    @Autowired
    private ActividadUsuarioService actividad_usuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<ActividadUsuario> listActividadUsuario() {
        return this.actividad_usuarioService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ActividadUsuario> view(@PathVariable Long id) {
        Optional<ActividadUsuario> optionalActividadUsuario = actividad_usuarioService.findById(id);
        if (optionalActividadUsuario.isPresent()) {
            return ResponseEntity.ok(optionalActividadUsuario.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody ActividadUsuario actividad_usuario, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(actividad_usuarioService.save(actividad_usuario));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ActividadUsuario> update(@PathVariable Long id, @Valid @RequestBody ActividadUsuario actividad_usuario) {
        Optional<ActividadUsuario> actividad_usuarioOptional = this.actividad_usuarioService.update(id, actividad_usuario);
        if (actividad_usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(actividad_usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<ActividadUsuario> delete(@PathVariable Long id) {
        Optional<ActividadUsuario> optionalActividadUsuario = this.actividad_usuarioService.delete(id);
        if (optionalActividadUsuario.isPresent()) {
            return ResponseEntity.ok(optionalActividadUsuario.orElseThrow());
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
