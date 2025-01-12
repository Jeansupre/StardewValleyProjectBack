package com.jean.stardew_valley_api.service.interfaces;

import com.jean.stardew_valley_api.dto.CrearAldeanoRequestDTO;
import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IAdminService {

    /**
     * Metodo para crear un usuario
     * @param crearUsuarioDTO DTO con los datos del usuario a crear
     * @return {@code true} si se creo el usuario, {@code false} si no
     */
    Boolean crearUsuario(CrearUsuarioDTO crearUsuarioDTO);

    /**
     * Metodo para crear un aldeano
     * @param crearAldeanoRequestDTO DTO con los datos del aldeano a crear
     * @param img Imagen del aldeano
     * @return {@code true} si se creo el aldeano, {@code false} si no
     */
    Boolean crearAldeano(CrearAldeanoRequestDTO crearAldeanoRequestDTO, MultipartFile img) throws IOException;
}
