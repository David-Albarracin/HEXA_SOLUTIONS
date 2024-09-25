package pro.ddsr.backend.modules.auth.dto;

import pro.ddsr.backend.modules.roles.entity.RolEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    Long id;
    String username;
    String password;
    RolEnum role;
}