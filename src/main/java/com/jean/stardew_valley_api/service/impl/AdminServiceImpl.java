package com.jean.stardew_valley_api.service.impl;

import com.jean.stardew_valley_api.dto.CrearAldeanoRequestDTO;
import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import com.jean.stardew_valley_api.exceptions.AuthException;
import com.jean.stardew_valley_api.mappers.IAldeanoMapper;
import com.jean.stardew_valley_api.mappers.IUsuarioMapper;
import com.jean.stardew_valley_api.model.Aldeano;
import com.jean.stardew_valley_api.model.Usuario;
import com.jean.stardew_valley_api.model.UsuarioRol;
import com.jean.stardew_valley_api.repository.IAldeanoRepository;
import com.jean.stardew_valley_api.repository.IUsuarioRepository;
import com.jean.stardew_valley_api.repository.IUsuarioRolRepository;
import com.jean.stardew_valley_api.service.interfaces.IAdminService;
import com.jean.stardew_valley_api.util.Constantes;
import com.jean.stardew_valley_api.util.ITools;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final IUsuarioRolRepository usuarioRolRepository;
    private final IAldeanoRepository aldeanoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    private final IAldeanoMapper aldeanoMapper;
    private final IUsuarioMapper usuarioMapper;

    private final HttpServletRequest httpServletRequest;

    /**
     * Metodo para crear un usuario
     * @param crearUsuarioDTO {@link CrearUsuarioDTO} DTO con los datos del usuario a crear
     * @return {@link Boolean} {@code true} si se creo el usuario, {@code false} si no
     */
    @Override
    @Transactional
    public Boolean crearUsuario(CrearUsuarioDTO crearUsuarioDTO) {
        try {
            verificacionAdmin();
            Usuario usuarioACrear = usuarioMapper.crearUsuarioDTOtoUsuario(crearUsuarioDTO);
            String hash = BCrypt.hashpw(usuarioACrear.getPassword(), BCrypt.gensalt());
            usuarioACrear.setPassword(hash);
            usuarioRepository.save(usuarioACrear);
        } catch (Exception e) {
            log.error(ITools.getMensaje("ERROR_CREATE_USER"), e);
            throw e;
        }
        return true;
    }

    /**
     * Metodo para crear un aldeano
     * @param crearAldeanoRequestDTO {@link CrearAldeanoRequestDTO} DTO con los datos del aldeano a crear
     * @param img {@link MultipartFile} Imagen del aldeano
     * @return {@link Boolean} {@code true} si se creo el aldeano, {@code false} si no
     */
    @Override
    @Transactional
    public Boolean crearAldeano(CrearAldeanoRequestDTO crearAldeanoRequestDTO, MultipartFile img) throws IOException {
        try {
            verificacionAdmin();
            byte[] imgByte = img.getBytes();
            Aldeano aldeanoCrear = aldeanoMapper.crearAldeanoRequestDTOtoAldeano(crearAldeanoRequestDTO);
            aldeanoCrear.setImagen(imgByte);
            aldeanoRepository.save(aldeanoCrear);
        } catch (Exception e) {
            log.error(ITools.getMensaje("ERROR_CREATE_VILLAGER"), e);
            throw e;
        }
        return true;
    }

    /**
     * Metodo para verificar si el usuario es administrador, toma el token del header Authorization y valida
     * si el usuario tiene el rol de ADMIN
     */
    private void verificacionAdmin() {
        String token = this.httpServletRequest.getHeader(Constantes.HEADER_TOKEN);
        String jwt = token.substring(7);
        if (!jwtService.isTokenValid(jwt)) {
            log.error(ITools.getMensaje("INVALID_TOKEN"));
            throw new AuthException(ITools.getMensaje("INVALID_TOKEN"));
        }
        Claims claims = jwtService.extractPayload(jwt);
        UsuarioRol usuarioRol = usuarioRolRepository
                .findUsuarioRolByIdUsuario(claims.get("ID_USUARIO", Long.class));
        if (!usuarioRol.getRol().getCodigo().equals("ADMIN")) {
            log.error(ITools.getMensaje("NOT_AUTH"));
            throw new AuthException(ITools.getMensaje("NOT_AUTH"));
        }
    }
}
