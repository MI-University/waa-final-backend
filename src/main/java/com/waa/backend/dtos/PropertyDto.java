package com.waa.backend.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.waa.backend.deserializer.StringOrNumberToDoubleDeserializer;
import com.waa.backend.deserializer.StringOrNumberToIntegerDeserializer;
import com.waa.backend.domains.Message;
import com.waa.backend.domains.Offer;
import com.waa.backend.domains.PropertyState;
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
    private List<MessageDto> messages;
    private PropertyState status;
    private String[] images;
}
