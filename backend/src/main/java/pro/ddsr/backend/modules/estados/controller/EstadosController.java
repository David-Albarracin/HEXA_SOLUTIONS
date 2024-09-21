
package pro.ddsr.backend.modules.estados.controller;

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

import pro.ddsr.backend.modules.estados.service.EstadosService;
import pro.ddsr.backend.modules.estados.entity.Estados;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadosController {

    @Autowired
    private EstadosService estadosService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Estados> listEstados() {
        return this.estadosService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Estados> view(@PathVariable Long id) {
        Optional<Estados> optionalEstados = estadosService.findById(id);
        if (optionalEstados.isPresent()) {
            return ResponseEntity.ok(optionalEstados.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Estados estados, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(estadosService.save(estados));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Estados> update(@PathVariable Long id, @Valid @RequestBody Estados estados) {
        Optional<Estados> estadosOptional = this.estadosService.update(id, estados);
        if (estadosOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(estadosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Estados> delete(@PathVariable Long id) {
        Optional<Estados> optionalEstados = this.estadosService.delete(id);
        if (optionalEstados.isPresent()) {
            return ResponseEntity.ok(optionalEstados.orElseThrow());
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
