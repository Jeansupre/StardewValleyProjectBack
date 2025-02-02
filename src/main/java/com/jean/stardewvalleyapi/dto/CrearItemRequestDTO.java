package com.jean.stardewvalleyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "CrearItemRequestDTO", description = "DTO para crear un item")
public class CrearItemRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4822877250683773556L;

    @Schema(description = "Nombre del item", example = "Manzana")
    private String nombre;

    @Schema(description = "Precio base del item", example = "100")
    private Long precioBase;

    @Schema(description = "Id de la categoria del item", example = "1")
    private Long idCategoria;

    @Schema(description = "Codigo del item", example = "MANZ")
    private String codigo;
}
