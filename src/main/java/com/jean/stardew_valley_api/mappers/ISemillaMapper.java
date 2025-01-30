package com.jean.stardew_valley_api.mappers;

import com.jean.stardew_valley_api.dto.CrearSemillaRequestDTO;
import com.jean.stardew_valley_api.model.Semilla;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ISemillaMapper {

    Semilla crearSemillaRequestDTOtoSemilla(CrearSemillaRequestDTO crearSemillaRequestDTO);
}