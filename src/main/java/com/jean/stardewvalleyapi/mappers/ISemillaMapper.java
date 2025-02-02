package com.jean.stardewvalleyapi.mappers;

import com.jean.stardewvalleyapi.dto.CrearSemillaRequestDTO;
import com.jean.stardewvalleyapi.model.Semilla;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ISemillaMapper {

    Semilla crearSemillaRequestDTOtoSemilla(CrearSemillaRequestDTO crearSemillaRequestDTO);
}