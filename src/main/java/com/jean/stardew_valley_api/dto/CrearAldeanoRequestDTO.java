package com.jean.stardew_valley_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Schema(name = "CrearAldeanoDTO", description = "DTO para crear un aldeano")
public class CrearAldeanoRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8765517780964208201L;

    @NotBlank
    @Schema(description = "Nombre del aldeano", example = "Robin")
    private String nombre;

    @NotNull
    @Schema(description = "ID de la estación en la que cumple años", example = "1")
    private Long idEstacionCumple;

    @NotNull
    @Schema(description = "Día de cumpleaños del aldeano", example = "10")
    private Long diaCumple;

    @NotNull
    @Schema(description = "ID de la dirección asociada al aldeano", example = "5")
    private Long idDireccion;
}
