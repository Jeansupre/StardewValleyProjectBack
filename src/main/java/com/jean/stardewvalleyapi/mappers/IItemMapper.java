package com.jean.stardewvalleyapi.mappers;

import com.jean.stardewvalleyapi.dto.CrearItemRequestDTO;
import com.jean.stardewvalleyapi.model.Item;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IItemMapper {

    @Mapping(target = "nombre", expression = "java(nameToUpperCase(crearItemRequestDTO.getNombre()))")
    Item crearItemRequestDTOtoItem(CrearItemRequestDTO crearItemRequestDTO);

    default String nameToUpperCase(String name) {
        return name.toUpperCase();
    }
}