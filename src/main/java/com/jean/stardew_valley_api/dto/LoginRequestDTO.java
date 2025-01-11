package com.jean.stardew_valley_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para el login de un usuario.")
public class LoginRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4430419028361125947L;

    @NotBlank
    @Schema(name = "username", description = "Nombre de usuario", example = "jean123")
    private String username;

    @NotBlank
    @Schema(name = "password", description = "Contraseña del usuario", example = "<PASSWORD>")
    private String password;
}
