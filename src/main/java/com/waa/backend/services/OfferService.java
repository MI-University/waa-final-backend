package com.waa.backend.services;


import com.waa.backend.dtos.OfferDto;
import com.waa.backend.request.OfferRequest;

import java.util.List;

public interface OfferService extends GenericCrudService<OfferRequest, OfferDto, Long>{

    OfferDto update(Long id, OfferRequest offerRequest) throws Exception;

    OfferDto acceptBySeller(Long offerId) throws Exception;

    OfferDto acceptByCustomer(Long offerId) throws Exception;


    OfferDto cancelByOwner(Long offerId) throws Exception;

    List<OfferDto> offerGetByPropertyId(Long id);
}
