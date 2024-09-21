
package pro.ddsr.backend.modules.grupos.controller;

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

import pro.ddsr.backend.modules.grupos.service.GruposService;
import pro.ddsr.backend.modules.grupos.entity.Grupos;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupos")
public class GruposController {

    @Autowired
    private GruposService gruposService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Grupos> listGrupos() {
        return this.gruposService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Grupos> view(@PathVariable Long id) {
        Optional<Grupos> optionalGrupos = gruposService.findById(id);
        if (optionalGrupos.isPresent()) {
            return ResponseEntity.ok(optionalGrupos.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Grupos grupos, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(gruposService.save(grupos));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Grupos> update(@PathVariable Long id, @Valid @RequestBody Grupos grupos) {
        Optional<Grupos> gruposOptional = this.gruposService.update(id, grupos);
        if (gruposOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(gruposOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Grupos> delete(@PathVariable Long id) {
        Optional<Grupos> optionalGrupos = this.gruposService.delete(id);
        if (optionalGrupos.isPresent()) {
            return ResponseEntity.ok(optionalGrupos.orElseThrow());
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
