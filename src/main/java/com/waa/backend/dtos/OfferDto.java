package com.waa.backend.dtos;

import com.waa.backend.domains.OfferState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    Long id;
    String details;
    String createdOn;
    String modifiedOn;
    Double offerAmount;
    PropertyDto property;
    UserDto customer;
    OfferState status;
}