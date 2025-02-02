package com.jean.stardewvalleyapi.service.impl;

import com.jean.stardewvalleyapi.dto.LoginRequestDTO;
import com.jean.stardewvalleyapi.exceptions.TechnicalException;
import com.jean.stardewvalleyapi.model.UsuarioRol;
import com.jean.stardewvalleyapi.repository.IUsuarioRolRepository;
import com.jean.stardewvalleyapi.service.interfaces.IAuthService;
import com.jean.stardewvalleyapi.util.ITools;
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
