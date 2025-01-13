package com.jean.stardew_valley_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1299647221682890689L;

    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("tipo")
    private String tipoError;

    @JsonProperty("uri")
    private String uriRequested;

    @JsonProperty("posibilidad_continuar")
    private boolean posibilidadContinuar;

}

