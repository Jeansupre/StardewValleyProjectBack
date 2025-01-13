package com.jean.stardew_valley_api.service.impl;

import com.jean.stardew_valley_api.dto.CrearAldeanoRequestDTO;
import com.jean.stardew_valley_api.dto.CrearCategoriasRequestDTO;
import com.jean.stardew_valley_api.dto.CrearItemRequestDTO;
import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import com.jean.stardew_valley_api.exceptions.AuthException;
import com.jean.stardew_valley_api.mappers.IAldeanoMapper;
import com.jean.stardew_valley_api.mappers.IItemMapper;
import com.jean.stardew_valley_api.mappers.IUsuarioMapper;
import com.jean.stardew_valley_api.model.*;
import com.jean.stardew_valley_api.repository.*;
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
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final IUsuarioRolRepository usuarioRolRepository;
    private final ICategoriaRepository categoriaRepository;
    private final IAldeanoRepository aldeanoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IItemRepository itemRepository;

    private final IAldeanoMapper aldeanoMapper;
    private final IUsuarioMapper usuarioMapper;
    private final IItemMapper itemMapper;

    private final JwtService jwtService;

    private final HttpServletRequest httpServletRequest;

    private static final String INVALID_TOKEN = "INVALID_TOKEN";

    /**
     * Metodo para verificar si el usuario es administrador, toma el token del header Authorization y valida
     * si el usuario tiene el rol de ADMIN
     */
    @Transactional
    public void verificacionAdmin() {
        String token = this.httpServletRequest.getHeader(Constantes.HEADER_TOKEN);
        if (token == null || !token.startsWith("Bearer ")) {
            log.error(ITools.getMensaje(INVALID_TOKEN));
            throw new AuthException(ITools.getMensaje(INVALID_TOKEN));
        }
        String jwt = token.substring(7);
        if (!jwtService.isTokenValid(jwt)) {
            log.error(ITools.getMensaje(INVALID_TOKEN));
            throw new AuthException(ITools.getMensaje(INVALID_TOKEN));
        }
        Claims claims = jwtService.extractPayload(jwt);
        UsuarioRol usuarioRol = usuarioRolRepository
                .findUsuarioRolByIdUsuario(claims.get("ID_USUARIO", Long.class));
        if (!usuarioRol.getRol().getCodigo().equals("ADMIN")) {
            log.error(ITools.getMensaje("NOT_AUTH"));
            throw new AuthException(ITools.getMensaje("NOT_AUTH"));
        }
    }

    /**
     * Metodo para crear un usuario
     *
     * @param crearUsuarioDTO {@link CrearUsuarioDTO} DTO con los datos del usuario a crear
     * @return {@link Boolean} {@code true} si se creo el usuario, {@code false} si no
     */
    @Override
    @Transactional
    public Boolean crearUsuario(CrearUsuarioDTO crearUsuarioDTO) {
        try {
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
     *
     * @param crearAldeanoRequestDTO {@link CrearAldeanoRequestDTO} DTO con los datos del aldeano a crear
     * @param img {@link MultipartFile} Imagen del aldeano
     * @return {@link Boolean} {@code true} si se creo el aldeano, {@code false} si no
     */
    @Override
    @Transactional
    public Boolean crearAldeano(CrearAldeanoRequestDTO crearAldeanoRequestDTO, MultipartFile img) throws IOException {
        try {
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
     * Metodo para crear una o varias categorías a la vez
     *
     * @param crearCategoriasRequestDTO DTO con los datos de las categorías a crear
     * @return {@code true} si se crearon las categorías
     */
    @Override
    @Transactional
    public Boolean crearCategorias(CrearCategoriasRequestDTO crearCategoriasRequestDTO) {
        try {
            List<String> nombres = crearCategoriasRequestDTO.getNombres();
            if (nombres == null || nombres.isEmpty()) {
                log.error(ITools.getMensaje("NO_CATEGORIES"));
                throw new IllegalArgumentException(ITools.getMensaje("NO_CATEGORIES"));
            }
            for (String nombre : nombres) {
                Categoria categoria = Categoria.builder().nombre(nombre).build();
                categoriaRepository.save(categoria);
            }
        } catch (Exception e) {
            log.error(ITools.getMensaje("ERROR_CREATE_CATEGORIES"), e);
            throw e;
        }
        return true;
    }

    /**
     * Metodo para crear un item
     *
     * @param crearItemRequestDTO DTO con los datos del item a crear
     * @param img                    Imagen del item
     * @return {@code true} si se creo el item
     */
    @Override
    public Boolean crearItem(CrearItemRequestDTO crearItemRequestDTO, MultipartFile img) throws IOException {
        try {
            byte[] imgByte = img.getBytes();
            Item itemCrear = itemMapper.crearItemRequestDTOtoItem(crearItemRequestDTO);
            itemCrear.setImagen(imgByte);
            itemRepository.save(itemCrear);
        } catch (Exception e) {
            log.error(ITools.getMensaje("ERROR_CREATE_ITEM"), e);
            throw e;
        }
        return true;
    }
}
