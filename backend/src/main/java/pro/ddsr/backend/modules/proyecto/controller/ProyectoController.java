
package pro.ddsr.backend.modules.proyecto.controller;

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

import pro.ddsr.backend.modules.proyecto.service.ProyectoService;
import pro.ddsr.backend.modules.proyecto.entity.Proyecto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Proyecto> listProyecto() {
        return this.proyectoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Proyecto> view(@PathVariable Long id) {
        Optional<Proyecto> optionalProyecto = proyectoService.findById(id);
        if (optionalProyecto.isPresent()) {
            return ResponseEntity.ok(optionalProyecto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Proyecto proyecto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoService.save(proyecto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Proyecto> update(@PathVariable Long id, @Valid @RequestBody Proyecto proyecto) {
        Optional<Proyecto> proyectoOptional = this.proyectoService.update(id, proyecto);
        if (proyectoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Proyecto> delete(@PathVariable Long id) {
        Optional<Proyecto> optionalProyecto = this.proyectoService.delete(id);
        if (optionalProyecto.isPresent()) {
            return ResponseEntity.ok(optionalProyecto.orElseThrow());
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
