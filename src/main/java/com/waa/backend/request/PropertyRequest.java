package com.waa.backend.request;

import com.waa.backend.domains.*;
import com.waa.backend.dtos.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {
    Integer noOfBedrooms;
    Double noOfBathrooms;
    Double plotSize;
    Double price;
    Double area;
    String title;
    String description;
    AddressDto address;
    String[] images;
    PropertyState propertyState;
}
