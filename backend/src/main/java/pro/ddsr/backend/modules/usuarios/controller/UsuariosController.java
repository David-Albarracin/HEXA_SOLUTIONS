
package pro.ddsr.backend.modules.usuarios.controller;

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

import pro.ddsr.backend.modules.usuarios.service.UsuariosService;
import pro.ddsr.backend.modules.usuarios.entity.Usuarios;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Usuarios> listUsuarios() {
        return this.usuariosService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Usuarios> view(@PathVariable Long id) {
        Optional<Usuarios> optionalUsuarios = usuariosService.findById(id);
        if (optionalUsuarios.isPresent()) {
            return ResponseEntity.ok(optionalUsuarios.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Usuarios usuarios, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuarios));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Usuarios> update(@PathVariable Long id, @Valid @RequestBody Usuarios usuarios) {
        Optional<Usuarios> usuariosOptional = this.usuariosService.update(id, usuarios);
        if (usuariosOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuariosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Usuarios> delete(@PathVariable Long id) {
        Optional<Usuarios> optionalUsuarios = this.usuariosService.delete(id);
        if (optionalUsuarios.isPresent()) {
            return ResponseEntity.ok(optionalUsuarios.orElseThrow());
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
