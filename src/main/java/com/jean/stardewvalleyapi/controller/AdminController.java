package com.jean.stardewvalleyapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.stardewvalleyapi.dto.*;
import com.jean.stardewvalleyapi.security.annotations.RequireJwtVerification;
import com.jean.stardewvalleyapi.service.interfaces.IAdminService;
import io.swagger.v3.oas.annotations.Parameter;
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
            parameters = {
                    @Parameter(name = "data", description = "Datos del aldeano", required = true,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CrearAldeanoRequestDTO.class)
                            )),
                    @Parameter(name = "img", description = "Imagen del aldeano", required = true,
                            content = @Content(
                                    mediaType = "multipart/form-data",
                                    schema = @Schema(implementation = MultipartFile.class)
                            ))
            },
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

    @Operation(
            summary = "crearItem",
            description = "Endpoint para crear un item",
            tags = {"Admin"},
            parameters = {
                    @Parameter(name = "data", description = "Datos del item", required = true,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CrearItemRequestDTO.class)
                            )),
                    @Parameter(name = "img", description = "Imagen del aldeano", required = true,
                            content = @Content(
                                    mediaType = "multipart/form-data",
                                    schema = @Schema(implementation = MultipartFile.class)
                            ))
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "true si se creó el item",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class)
                            ))
            }
    )
    @PostMapping("/crearItem")
    public ResponseEntity<Boolean> crearItem(@RequestPart("data") String jsonData,
                                             @RequestPart("img") MultipartFile img) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CrearItemRequestDTO crearItemRequestDTO = mapper.readValue(jsonData, CrearItemRequestDTO.class);

        Boolean resultado = adminService.crearItem(crearItemRequestDTO, img);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Operation(
            summary = "crearSemilla",
            description = "Endpoint para crear una semilla",
            tags = {"Admin"},
            parameters = {
                    @Parameter(name = "data", description = "Datos de la semilla", required = true,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CrearSemillaRequestDTO.class)
                            )),
                    @Parameter(name = "img", description = "Imagen de la semilla", required = true,
                            content = @Content(
                                    mediaType = "multipart/form-data",
                                    schema = @Schema(implementation = MultipartFile.class)
                            ))
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "true si se creó la semilla",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class)
                            ))
            }
    )
    @PostMapping("/crearSemilla")
    public ResponseEntity<Boolean> crearSemilla(@RequestPart("data") String jsonData,
                                                @RequestPart("img") MultipartFile img) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CrearSemillaRequestDTO crearSemillaRequestDTO = mapper.readValue(jsonData, CrearSemillaRequestDTO.class);

        Boolean resultado = adminService.crearSemilla(crearSemillaRequestDTO, img);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    /*@PostMapping("/crearCultivo")
    public ResponseEntity<Boolean> crearCultivo() {
        Boolean resultado = adminService.crearCultivo();
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }*/
}
