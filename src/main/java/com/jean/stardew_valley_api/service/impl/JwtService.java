package com.jean.stardew_valley_api.service.impl;

import com.jean.stardew_valley_api.exceptions.TechnicalException;
import com.jean.stardew_valley_api.model.UsuarioRol;
import com.jean.stardew_valley_api.util.ITools;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Setter
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String JWT_ERROR = "JWT_ERROR";


    @Value("${authorization.seed}")
    String secret;

    @Value("${authorization.expiration}")
    long expirationTime;

    /**
     * Genera un token JWT
     * @param usuarioRol {@link UsuarioRol} Usuario y rol a generar el token
     * @return {@link String} Token generado
     */
    public String generateToken(UsuarioRol usuarioRol) {
        try {
            var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            return Jwts.builder()
                    .header().type("JWT").and()
                    .claim("ID_USUARIO", usuarioRol.getUsuario().getId())
                    .claim("ID_ROL", usuarioRol.getRol().getId())
                    .expiration(new Date(System.currentTimeMillis() + expirationTime))
                    .issuedAt(new Date())
                    .signWith(key, Jwts.SIG.HS256)
                    .compact();
        } catch (Exception e) {
            log.error(ITools.getMensaje(JWT_ERROR), e);
            throw new TechnicalException(ITools.getMensaje(JWT_ERROR), e);
        }
    }

    /**
     * Extrae el payload del token
     * @param token Token a extraer
     * @return {@link Claims} Payload del token
     */
    public Claims extractPayload(String token) {
        return extractAllClaims(token);
    }

    /**
     * Verifica si el token es valido
     * @param token Token a verificar
     * @return {@code true} si el token es valido, {@code false} si no
     */
    public boolean isTokenValid(String token) {
        final Claims claims = extractPayload(token);
        return !isTokenExpired(claims);
    }

    /**
     * Extrae todos los claims del token
     * @param token Token a extraer
     * @return {@link Claims} Claims del token
     * @throws TechnicalException Arroja {@link TechnicalException} si ocurre un error al extraer los claims
     */
    private Claims extractAllClaims(String token) throws TechnicalException {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            log.error(ITools.getMensaje(JWT_ERROR), e);
            throw new TechnicalException(ITools.getMensaje(JWT_ERROR), e);
        }
    }

    /**
     * Verifica si el token ha expirado
     * @param claims {@link Claims} Claims del token
     * @return {@code true} si el token ha expirado, {@code false} si no
     */
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
