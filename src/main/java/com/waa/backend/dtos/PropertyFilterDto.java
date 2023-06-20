package com.waa.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyFilterDto {

    private Long maxPrice;
    private Long minPrice;
    private String city;
    private int noOfBedrooms;
}
