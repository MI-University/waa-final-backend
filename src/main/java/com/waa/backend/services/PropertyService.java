package com.waa.backend.services;

import com.waa.backend.dtos.PropertyDto;
import com.waa.backend.dtos.PropertyFilterDto;
import com.waa.backend.request.PropertyRequest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PropertyService extends GenericCrudService<PropertyRequest, PropertyDto, Long> {
    List<PropertyDto> getAll(PropertyFilterDto filterDataDto);

    PropertyDto soldProperty(Long id, Long offerId) throws ChangeSetPersister.NotFoundException;
}
