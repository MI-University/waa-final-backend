package com.waa.backend.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperHelper<E, DTO> {

    @Autowired
    private ModelMapper modelMapper;

    public <E, DTO> DTO convertToDto(E entity, Class<DTO> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <E, DTO> E convertToEntity(DTO dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <E, DTO> List<DTO> convertToDtoList(List<E> entityList, Class<DTO> dtoClass) {
        return entityList.stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public <E, DTO> List<E> convertToEntityList(List<DTO> dtoList, Class<E> entityClass) {
        return dtoList.stream()
                .map(dto -> convertToEntity(dto, entityClass))
                .collect(Collectors.toList());
    }

    public <REQ> void updateEntityFromDto(REQ dto, E entity) {
        // Retrieve the list of declared fields in the DTO class
        Field[] dtoFields = dto.getClass().getDeclaredFields();

        // Retrieve the list of declared fields in the entity class
        Field[] entityFields = entity.getClass().getDeclaredFields();

        // Iterate over the fields in the DTO class
        for (Field dtoField : dtoFields) {
            try {
                // Access the field and make it accessible
                dtoField.setAccessible(true);

                // Retrieve the field name and value from the DTO
                String fieldName = dtoField.getName();
                Object fieldValue = dtoField.get(dto);

                // Find the corresponding field in the entity class
                for (Field entityField : entityFields) {
                    // Access the field and make it accessible
                    entityField.setAccessible(true);

                    // Check if the field names match
                    if (entityField.getName().equals(fieldName)) {
                        // Set the field value in the entity
                        entityField.set(entity, fieldValue);
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                // Handle any reflection-related exceptions
                e.printStackTrace();
            }
        }
    }

}

