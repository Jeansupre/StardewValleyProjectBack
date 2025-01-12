package com.jean.stardew_valley_api.mappers;

import com.jean.stardew_valley_api.dto.CrearAldeanoRequestDTO;
import com.jean.stardew_valley_api.model.Aldeano;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IAldeanoMapper {

    @Mapping(target = "nombre", expression = "java(nameToUpperCase(crearAldeanoRequestDTO.getNombre()))")
    Aldeano crearAldeanoRequestDTOtoAldeano(CrearAldeanoRequestDTO crearAldeanoRequestDTO);

    default String nameToUpperCase(String name) {
        return name.toUpperCase();
    }
}