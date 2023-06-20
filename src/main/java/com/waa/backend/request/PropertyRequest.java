package com.waa.backend.request;

import com.waa.backend.domains.*;
import com.waa.backend.dtos.AddressDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {
    Long userId;
    Integer noOfBedrooms;
    Double noOfBathrooms;
    Double plotSize;
    Double price;
    Double area;
    String title;
    String description;
    AddressDto addressDto;
    List<BlobStorageInfo> images;
    PropertyState propertyState;
}
