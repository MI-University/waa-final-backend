package com.waa.backend.dtos;

import com.waa.backend.domains.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    private Long id;
    private Integer noOfBedrooms;
    private Double noOfBathrooms;
    private Double plotSize;
    private Double price;
    private Double area;
    private String title;
    private String description;
    private Address address;
    private User user;
    private List<Offer> offers;
    private PropertyState status;
    private String[]  images;
}
