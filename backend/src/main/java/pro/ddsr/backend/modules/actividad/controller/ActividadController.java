
package pro.ddsr.backend.modules.actividad.controller;

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

import pro.ddsr.backend.modules.actividad.service.ActividadService;
import pro.ddsr.backend.modules.actividad.entity.Actividad;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Actividad> listActividad() {
        return this.actividadService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Actividad> view(@PathVariable Long id) {
        Optional<Actividad> optionalActividad = actividadService.findById(id);
        if (optionalActividad.isPresent()) {
            return ResponseEntity.ok(optionalActividad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Actividad actividad, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(actividadService.save(actividad));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Actividad> update(@PathVariable Long id, @Valid @RequestBody Actividad actividad) {
        Optional<Actividad> actividadOptional = this.actividadService.update(id, actividad);
        if (actividadOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(actividadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Actividad> delete(@PathVariable Long id) {
        Optional<Actividad> optionalActividad = this.actividadService.delete(id);
        if (optionalActividad.isPresent()) {
            return ResponseEntity.ok(optionalActividad.orElseThrow());
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
