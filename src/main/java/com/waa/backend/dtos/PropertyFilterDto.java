package com.waa.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyFilterDto {

    private Long maxPrice;
    private Long minPrice;
    private String city;
    private int noOfBedrooms;
}
