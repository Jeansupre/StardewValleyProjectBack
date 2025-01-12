package com.jean.stardew_valley_api.mappers;

import com.jean.stardew_valley_api.dto.CrearUsuarioDTO;
import com.jean.stardew_valley_api.model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUsuarioMapper {

    Usuario crearUsuarioDTOtoUsuario(CrearUsuarioDTO crearUsuarioDTO);
}