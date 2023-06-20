package com.waa.backend.services.impl;

import com.waa.backend.domains.Address;
import com.waa.backend.dtos.AddressDto;
import com.waa.backend.repositories.AddressRepository;
import com.waa.backend.services.AddressService;
import com.waa.backend.util.ModelMapperHelper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends GenericCrudServiceImpl<Address, AddressDto, AddressDto, Long> implements AddressService {
    private final AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository, ModelMapperHelper<Address, AddressDto> modelMapperHelper) {
        super(repository, modelMapperHelper, Address.class, AddressDto.class);
        this.repository = repository;
    }
//
//    /**
//     * @param addressDto
//     * @return Address
//     */
//    @Override
//    public Address createAndGetAddress(AddressDto addressDto) {
//        this.create(addressDto);
//        return repository.findById(addressDto.getId()).orElseThrow(() -> new RuntimeException("Unable to save the address"));
//    }
}

