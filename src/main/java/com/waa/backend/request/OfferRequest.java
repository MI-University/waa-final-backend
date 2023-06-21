package com.waa.backend.request;

import com.waa.backend.domains.OfferState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequest {
    Long id;
    Double offerAmount;
    Long propertyId;
    String details;
    OfferState status;
}
