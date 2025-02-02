package com.jean.stardewvalleyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CrearCategoriasRequestDTO", description = "DTO para crear varias categorias")
public class CrearCategoriasRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4822877250683773556L;

    @Schema(description = "Nombres de las categorias", example = "[\"Frutas\", \"Cocina\"]")
    private List<String> nombres;
}
