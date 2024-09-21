
package pro.ddsr.backend.modules.roles.controller;

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

import pro.ddsr.backend.modules.roles.service.RolesService;
import pro.ddsr.backend.modules.roles.entity.Roles;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Roles> listRoles() {
        return this.rolesService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Roles> view(@PathVariable Long id) {
        Optional<Roles> optionalRoles = rolesService.findById(id);
        if (optionalRoles.isPresent()) {
            return ResponseEntity.ok(optionalRoles.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Roles roles, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(rolesService.save(roles));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Roles> update(@PathVariable Long id, @Valid @RequestBody Roles roles) {
        Optional<Roles> rolesOptional = this.rolesService.update(id, roles);
        if (rolesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(rolesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Roles> delete(@PathVariable Long id) {
        Optional<Roles> optionalRoles = this.rolesService.delete(id);
        if (optionalRoles.isPresent()) {
            return ResponseEntity.ok(optionalRoles.orElseThrow());
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
