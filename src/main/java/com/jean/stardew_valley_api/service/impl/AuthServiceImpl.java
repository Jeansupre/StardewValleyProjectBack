package com.jean.stardew_valley_api.service.impl;

import com.jean.stardew_valley_api.dto.LoginRequestDTO;
import com.jean.stardew_valley_api.exceptions.TechnicalException;
import com.jean.stardew_valley_api.model.UsuarioRol;
import com.jean.stardew_valley_api.repository.IUsuarioRolRepository;
import com.jean.stardew_valley_api.service.interfaces.IAuthService;
import com.jean.stardew_valley_api.util.ITools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final JwtService jwtService;
    private final IUsuarioRolRepository usuarioRolRepository;

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        UsuarioRol usuarioRol = checkearUsuario(loginRequestDTO);
        return jwtService.generateToken(usuarioRol);
    }

    private UsuarioRol checkearUsuario(LoginRequestDTO loginRequestDTO) {
        if (loginRequestDTO == null) {
            throw new TechnicalException(ITools.getMensaje("OBJECT_NULL"));
        }
        UsuarioRol usuarioRol = usuarioRolRepository.findUsuarioByUsername(loginRequestDTO.getUsername());
        //check password
        if(!BCrypt.checkpw(loginRequestDTO.getPassword(), usuarioRol.getUsuario().getPassword())){
            throw new TechnicalException(ITools.getMensaje("PASSWORD_INCORRECT"));
        }
        return usuarioRol;
    }
}
