package com.jean.stardew_valley_api.controller;

import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import com.jean.stardew_valley_api.service.interfaces.IAdminService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    @Operation(
            summary = "crearUsuario",
            description = "Endpoint para crear un usuario",
            tags = {"Admin"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado",
                            content = @Content(mediaType = "text/plain")),
                    @ApiResponse(responseCode = "409", description = "Error al crear el usuario",
                            content = @Content(mediaType = "text/plain"))
            }
    )
    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        var resultado = adminService.crearUsuario(crearUsuarioDTO);
        if (Boolean.TRUE.equals(resultado)) {
            return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el usuario", HttpStatus.CONFLICT);
        }
    }


}
