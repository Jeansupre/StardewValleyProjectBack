package com.jean.stardewvalleyapi.mappers;

import com.jean.stardewvalleyapi.dto.CrearAldeanoRequestDTO;
import com.jean.stardewvalleyapi.model.Aldeano;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IAldeanoMapper {

    @Mapping(target = "nombre", expression = "java(nameToUpperCase(crearAldeanoRequestDTO.getNombre()))")
    Aldeano crearAldeanoRequestDTOtoAldeano(CrearAldeanoRequestDTO crearAldeanoRequestDTO);

    default String nameToUpperCase(String name) {
        return name.toUpperCase();
    }
}