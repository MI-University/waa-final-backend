package com.waa.backend.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperHelper{

    @Autowired
    private ModelMapper modelMapper;

    public <T, E> E convertToDto(T entity, Class<E> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <T, E> T convertToEntity(E dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <T, E> List<E> convertToDtoList(List<T> entityList, Class<E> dtoClass) {
        return entityList.stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public <T, E> List<T> convertToEntityList(List<E> dtoList, Class<T> entityClass) {
        return dtoList.stream()
                .map(dto -> convertToEntity(dto, entityClass))
                .collect(Collectors.toList());
    }
}

