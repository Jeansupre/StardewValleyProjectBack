package com.jean.stardew_valley_api.service.impl;

import com.jean.stardew_valley_api.model.UsuarioRol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Setter
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${authorization.seed}")
    String secret;

    @Value("${authorization.expiration}")
    long expirationTime;

    public String generateToken(UsuarioRol usuarioRol) {
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .header().type("JWT").and()
                .claim("ID_USUARIO", usuarioRol.getUsuario().getId())
                .claim("ID_ROL", usuarioRol.getRol().getId())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .issuedAt(new Date())
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public Claims extractPayload(String token) {
        return extractAllClaims(token);
    }

    public boolean isTokenValid(String token) {
        final Claims claims = extractPayload(token);
        return !isTokenExpired(claims);
    }

    private Claims extractAllClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
