package com.jean.stardew_valley_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.stardew_valley_api.dto.CrearAldeanoRequestDTO;
import com.jean.stardew_valley_api.dto.CrearCategoriasRequestDTO;
import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import com.jean.stardew_valley_api.security.annotations.RequireJwtVerification;
import com.jean.stardew_valley_api.service.interfaces.IAdminService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequireJwtVerification
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    @Operation(
            summary = "crearUsuario",
            description = "Endpoint para crear un usuario",
            tags = {"Admin"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "true si se creo el usuario",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class)
                            ))
            }
    )
    @PostMapping("/crearUsuario")
    public ResponseEntity<Boolean> crearUsuario(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        Boolean resultado = adminService.crearUsuario(crearUsuarioDTO);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);

    }

    @Operation(
            summary = "crearAldeano",
            description = "Endpoint para crear un aldeano",
            tags = {"Admin"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "true si se creo el aldeano",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class)
                            ))
            }
    )
    @PostMapping("/crearAldeano")
    public ResponseEntity<Boolean> crearAldeano(@RequestPart("data") String jsonData,
                                               @RequestPart("img") MultipartFile img) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CrearAldeanoRequestDTO crearAldeanoRequestDTO = mapper.readValue(jsonData, CrearAldeanoRequestDTO.class);

        Boolean resultado = adminService.crearAldeano(crearAldeanoRequestDTO, img);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Operation(
            summary = "crearCategorias",
            description = "Endpoint para crear una o varias categorias a la vez",
            tags = {"Admin"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "true si se crearon las categorias",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class)
                            ))
            }
    )
    @PostMapping("/crearCategorias")
    public ResponseEntity<Boolean> crearCategorias(@RequestBody CrearCategoriasRequestDTO crearCategoriasRequestDTO) {
        Boolean resultado = adminService.crearCategorias(crearCategoriasRequestDTO);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    /*@PostMapping("/crearItem")
    public ResponseEntity<Boolean> crearItem() {
        Boolean resultado = adminService.crearItem();
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }*/

    /*@PostMapping("/crearCultivo")
    public ResponseEntity<Boolean> crearCultivo() {
        Boolean resultado = adminService.crearCultivo();
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }*/
}
