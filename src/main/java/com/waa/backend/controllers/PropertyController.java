
package com.waa.backend.controllers;

import com.waa.backend.apiresponse.ApiResponse;
import com.waa.backend.dtos.PropertyDto;
import com.waa.backend.request.PropertyRequest;
import com.waa.backend.services.PropertyService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController extends GenericCrudControllerImpl<PropertyRequest, PropertyDto, Long, PropertyService> {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        super(propertyService, "Property");
        this.propertyService = propertyService;
    }

    @GetMapping("/{propertyId}/offers/{offerId}/sold")
    public ResponseEntity<ApiResponse<PropertyDto>> checkOfferHistoryByPropertyId(@PathVariable Long propertyId, @PathVariable Long offerId) throws ChangeSetPersister.NotFoundException {
        PropertyDto offers = this.propertyService.soldProperty(propertyId, offerId);
        return ResponseEntity.ok(ApiResponse.success("Property sold successfully.", offers));
    }
}