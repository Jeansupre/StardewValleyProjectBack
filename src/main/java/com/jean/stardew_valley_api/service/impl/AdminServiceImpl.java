package com.jean.stardew_valley_api.service.impl;

import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import com.jean.stardew_valley_api.exceptions.AuthException;
import com.jean.stardew_valley_api.mappers.IUsuarioMapper;
import com.jean.stardew_valley_api.model.Usuario;
import com.jean.stardew_valley_api.model.UsuarioRol;
import com.jean.stardew_valley_api.repository.IUsuarioRepository;
import com.jean.stardew_valley_api.repository.IUsuarioRolRepository;
import com.jean.stardew_valley_api.service.interfaces.IAdminService;
import com.jean.stardew_valley_api.util.Constantes;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final IUsuarioRolRepository usuarioRolRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioMapper usuarioMapper;
    private final JwtService jwtService;

    private final HttpServletRequest httpServletRequest;

    @Override
    @Transactional
    public Boolean crearUsuario(CrearUsuarioDTO crearUsuarioDTO) {
        try {
            String token = this.httpServletRequest.getHeader(Constantes.HEADER_TOKEN);
            String jwt = token.substring(7);
            if (!jwtService.isTokenValid(jwt)) {
                throw new AuthException();
            }
            Claims claims = jwtService.extractPayload(jwt);
            UsuarioRol usuarioRol = usuarioRolRepository
                    .findUsuarioRolByIdUsuario(claims.get("ID_USUARIO", Long.class));
            if (usuarioRol.getRol().getCodigo().equals("ADMIN")) {
                Usuario usuarioACrear = usuarioMapper.crearUsuarioDTOtoUsuario(crearUsuarioDTO);
                String hash = BCrypt.hashpw(usuarioACrear.getPassword(), BCrypt.gensalt());
                usuarioACrear.setPassword(hash);
                usuarioRepository.save(usuarioACrear);
            } else {
                throw new AuthException();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
