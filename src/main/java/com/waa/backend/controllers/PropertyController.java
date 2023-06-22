
package com.waa.backend.controllers;

import com.waa.backend.dtos.PropertyDto;
import com.waa.backend.request.PropertyRequest;
import com.waa.backend.services.PropertyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController extends GenericCrudControllerImpl<PropertyRequest, PropertyDto, Long, PropertyService> {

    public PropertyController(PropertyService propertyService) {
        super(propertyService, "Property");
    }
}