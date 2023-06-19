package com.waa.backend.services.impl;

import com.waa.backend.exceptions.ResourceNotFoundException;
import com.waa.backend.services.GenericCrudService;
import com.waa.backend.util.ModelMapperHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
public abstract class GenericCrudServiceImpl<E, REQ, DTO, ID> implements GenericCrudService<REQ, DTO, ID> {

    private final JpaRepository<E, ID> repository;
    private final ModelMapperHelper<E, DTO> modelMapperHelper;
    private final Class<E> eClass;
    private final Class<DTO> dtoClass;


    public List<DTO> getAll() {
        List<E> entities = repository.findAll();
        return modelMapperHelper.convertToDtoList(entities, dtoClass);
    }

    @Override
    public DTO getById(ID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" Entity not found with id: " + id));
        return modelMapperHelper.convertToDto(entity, dtoClass);
    }

    @Override
    public DTO create(REQ req) {
        E entity = modelMapperHelper.convertToEntity(req, eClass);
        E saveEntity = repository.save(entity);
        return modelMapperHelper.convertToDto(saveEntity, dtoClass);
    }

    @Override
    public DTO update(REQ req, ID id) {
        E existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));

        // Update the fields of the existing entity with the values from the request object
        updateEntityFromRequest(req, existingEntity);

        // Save the updated entity
        E savedEntity = repository.save(existingEntity);

        // Convert the saved entity back to a DTO
        return modelMapperHelper.convertToDto(savedEntity, dtoClass);
    }


    @Override
    public boolean delete(ID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cost not found with id: " + id));

        repository.delete(entity);
        return true;
    }

    private void updateEntityFromRequest(REQ req, E entity) {
        // Retrieve the list of declared fields in the request object class
        Field[] requestFields = req.getClass().getDeclaredFields();

        // Retrieve the list of declared fields in the entity class
        Field[] entityFields = entity.getClass().getDeclaredFields();

        // Iterate over the fields in the request object class
        for (Field requestField : requestFields) {
            try {
                // Access the field and make it accessible
                requestField.setAccessible(true);

                // Retrieve the field name and value from the request object
                String fieldName = requestField.getName();
                Object fieldValue = requestField.get(req);

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
