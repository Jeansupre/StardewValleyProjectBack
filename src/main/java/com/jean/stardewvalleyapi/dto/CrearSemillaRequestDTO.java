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
@Schema(name = "CrearSemillaRequestDTO", description = "DTO para crear una semilla")
public class CrearSemillaRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7538802291070642168L;

    @Schema(description = "Nombre de la semilla", example = "Semilla de manzana")
    private String nombre;

    @Schema(description = "Precio de venta de la semilla", example = "100")
    private Long precioCompra;

    @Schema(description = "Codigo de texto", example = "SEMILLA_MANZANA")
    private String codigo;
}
