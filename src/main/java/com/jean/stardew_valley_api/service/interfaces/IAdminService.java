package com.jean.stardew_valley_api.service.interfaces;

import com.jean.stardew_valley_api.dto.CrearAldeanoRequestDTO;
import com.jean.stardew_valley_api.dto.CrearCategoriasRequestDTO;
import com.jean.stardew_valley_api.dto.CrearItemRequestDTO;
import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IAdminService {

    /**
     * Metodo para crear un usuario
     * @param crearUsuarioDTO DTO con los datos del usuario a crear
     * @return {@code true} si se creo el usuario
     */
    Boolean crearUsuario(CrearUsuarioDTO crearUsuarioDTO);

    /**
     * Metodo para crear un aldeano
     * @param crearAldeanoRequestDTO DTO con los datos del aldeano a crear
     * @param img Imagen del aldeano
     * @return {@code true} si se creo el aldeano
     */
    Boolean crearAldeano(CrearAldeanoRequestDTO crearAldeanoRequestDTO, MultipartFile img) throws IOException;

    /**
     * Metodo para crear una  o varias categorías a la vez
     * @param crearCategoriasRequestDTO DTO con los datos de las categorías a crear
     * @return {@code true} si se crearon las categorías
     */
    Boolean crearCategorias(CrearCategoriasRequestDTO crearCategoriasRequestDTO);

    /**
     * Metodo para crear un item
     * @param crearItemRequestDTO DTO con los datos del item a crear
     * @param img Imagen del item
     * @return {@code true} si se creo el item
     */
    Boolean crearItem(CrearItemRequestDTO crearItemRequestDTO, MultipartFile img) throws IOException;
}
