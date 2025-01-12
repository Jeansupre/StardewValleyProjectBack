package com.jean.stardew_valley_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.stardew_valley_api.dto.CrearAldeanoRequestDTO;
import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import com.jean.stardew_valley_api.service.interfaces.IAdminService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
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

    @PostMapping("/crearAldeano")
    public ResponseEntity<String> crearAldeano(@RequestPart("data") String jsonData,
                                               @RequestPart("img") MultipartFile img) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CrearAldeanoRequestDTO crearAldeanoRequestDTO = mapper.readValue(jsonData, CrearAldeanoRequestDTO.class);

        var resultado = adminService.crearAldeano(crearAldeanoRequestDTO, img);
        if (Boolean.TRUE.equals(resultado)) {
            return new ResponseEntity<>("Aldeano creado", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el aldeano", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/crearCultivo")
    public ResponseEntity<String> crearCultivo() {
        return new ResponseEntity<>("Cultivo creado", HttpStatus.CREATED);
    }

    @PostMapping("/crearItem")
    public ResponseEntity<String> crearItem() {
        return new ResponseEntity<>("Item creado", HttpStatus.CREATED);
    }
}
