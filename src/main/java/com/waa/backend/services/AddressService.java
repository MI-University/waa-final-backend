package com.waa.backend.services;

import com.waa.backend.domains.Address;
import com.waa.backend.dtos.AddressDto;


public interface AddressService extends GenericCrudService<AddressDto, AddressDto, Long> {
    Address createAndGetAddress(AddressDto addressDto);
}
