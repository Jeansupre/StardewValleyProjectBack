package com.jean.stardew_valley_api.mappers;

import com.jean.stardew_valley_api.dto.CrearItemRequestDTO;
import com.jean.stardew_valley_api.model.Item;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IItemMapper {

    @Mapping(target = "nombre", expression = "java(nameToUpperCase(crearItemRequestDTO.getNombre()))")
    Item crearItemRequestDTOtoItem(CrearItemRequestDTO crearItemRequestDTO);

    default String nameToUpperCase(String name) {
        return name.toUpperCase();
    }
}