package com.jean.stardewvalleyapi.mappers;

import com.jean.stardewvalleyapi.dto.CrearUsuarioDTO;
import com.jean.stardewvalleyapi.model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUsuarioMapper {

    Usuario crearUsuarioDTOtoUsuario(CrearUsuarioDTO crearUsuarioDTO);
}