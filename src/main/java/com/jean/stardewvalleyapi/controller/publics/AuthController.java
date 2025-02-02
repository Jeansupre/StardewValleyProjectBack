package com.jean.stardewvalleyapi.controller.publics;

import com.jean.stardewvalleyapi.dto.LoginRequestDTO;
import com.jean.stardewvalleyapi.service.interfaces.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @Operation(
            summary = "Login",
            description = "Endpoint para hacer el login",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login exitoso"),
            }

    )
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        String jwt = authService.login(loginRequestDTO);
        var response = new HashMap<String, Object>();
        response.put("token", jwt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
