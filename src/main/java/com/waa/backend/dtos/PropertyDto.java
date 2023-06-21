package com.waa.backend.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.waa.backend.domains.Address;
import com.waa.backend.domains.PropertyState;
import com.waa.backend.domains.User;
import com.waa.backend.deserializer.StringOrNumberToDoubleDeserializer;
import com.waa.backend.deserializer.StringOrNumberToIntegerDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    private Long id;
    @JsonDeserialize(using = StringOrNumberToIntegerDeserializer.class)
    private Integer noOfBedrooms;
    @JsonDeserialize(using = StringOrNumberToDoubleDeserializer.class)
    private Double noOfBathrooms;
    @JsonDeserialize(using = StringOrNumberToDoubleDeserializer.class)
    private Double plotSize;
    @JsonDeserialize(using = StringOrNumberToDoubleDeserializer.class)
    private Double price;
    @JsonDeserialize(using = StringOrNumberToDoubleDeserializer.class)
    private Double area;
    private String title;
    private String description;
    private AddressDto address;
    private UserDto user;
    //    private List<Offer> offers;
    private PropertyState status;
//    private String[]  images;
}
