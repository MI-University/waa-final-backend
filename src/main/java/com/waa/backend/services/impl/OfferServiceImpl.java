package com.waa.backend.services.impl;

import com.waa.backend.domains.*;
import com.waa.backend.dtos.OfferDto;
import com.waa.backend.repositories.OfferRepository;
import com.waa.backend.repositories.PropertyRepository;
import com.waa.backend.request.OfferRequest;
import com.waa.backend.services.MessageService;
import com.waa.backend.services.OfferService;
import com.waa.backend.services.UserService;
import com.waa.backend.util.AUTH;
import com.waa.backend.util.ModelMapperHelper;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class OfferServiceImpl extends GenericCrudServiceImpl<Offer, OfferRequest, OfferDto, Long> implements OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    MessageService messageService;

    public OfferServiceImpl(JpaRepository<Offer, Long> repository, ModelMapperHelper<Offer, OfferDto> modelMapperHelper) {
        super(repository, modelMapperHelper, Offer.class, OfferDto.class);
    }


    /**
     * @param filterData
     * @return
     */
    @Override
    public List<OfferDto> getAll(OfferDto filterData) {
        if (AUTH.getUserDetails().getRole() == Role.CUSTOMER) {
            return offerRepository.findOffersByUserId(AUTH.getUserDetails().getId()).stream().map(offer -> this.modelMapper.map(offer, OfferDto.class)).toList();
        } else if (AUTH.getUserDetails().getRole() == Role.OWNER) {
            return offerRepository.findOffersByOwnerId(AUTH.getUserDetails().getId()).stream().map(
                    offer -> this.modelMapper.map(offer, OfferDto.class)
            ).toList();
        }
        return null;
    }

    @Override
    public OfferDto create(OfferRequest offerRequest) throws Exception {
        Property property = propertyRepository.findById(offerRequest.getPropertyId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if (!(property.getStatus() == PropertyState.PENDING ||
                property.getStatus() == PropertyState.AVAILABLE)) {
            throw new Exception("Offers are not accepted.");
        }
        Offer offer = new Offer();
        offer.setUser(this.modelMapper.map(userService.getById(AUTH.getUserDetails().getId()), User.class));
        offer.setProperty(property);
        offer.setOfferAmount(offerRequest.getOfferAmount());
        offer.setOfferAmount(offerRequest.getOfferAmount());
        offer.setDetails(offerRequest.getDetails());
        offer.setStatus(OfferState.PENDING);
        return this.modelMapper.map(offerRepository.save(offer), OfferDto.class);
    }

    /**
     * @param id
     * @param offerRequest
     * @return
     */
    @Override
    public OfferDto update(Long id, OfferRequest offerRequest) throws Exception {
        Offer offer = offerRepository.findById(id).orElseThrow();
        if (Objects.equals(offer.getUser().getId(), AUTH.getUserDetails().getId())) {
            offer.setOfferAmount(offerRequest.getOfferAmount());
            offer.setDetails(offerRequest.getDetails());
        } else {
            throw new Exception("Illegal access");
        }
        return this.modelMapper.map(offerRepository.save(offer), OfferDto.class);
    }

    @Override
    public OfferDto acceptBySeller(Long offerId) throws Exception {
        Offer offer = offerRepository.findById(offerId).orElseThrow();

        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if (Objects.equals(offer.getProperty().getUser().getId(), AUTH.getUserDetails().getId())) {
            if (!(property.getStatus() == PropertyState.PENDING ||
                    property.getStatus() == PropertyState.AVAILABLE)) {
                throw new Exception("Offers are not accepted.");
            }
            offer.setStatus(OfferState.PENDING);
            property.setStatus(PropertyState.PENDING);
        } else {
            throw new Exception("You are not authorized");
        }
        offerRepository.save(offer);
        return this.modelMapper.map(offer, OfferDto.class);
    }

    @Override
    public OfferDto acceptByCustomer(Long offerId) throws Exception {
        Offer offer = offerRepository.findById(offerId).orElseThrow();

        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if (Objects.equals(offer.getUser().getId(), AUTH.getUserDetails().getId())) {
            if (offer.getStatus() == OfferState.PENDING && property.getStatus() == PropertyState.PENDING) {
                offer.setStatus(OfferState.ACCEPTED);
                property.setStatus(PropertyState.CONTINGENT);
            } else {
                throw new Exception("Offers are not accepted.");
            }
        } else {
            throw new Exception("You are not authorized");
        }

        offerRepository.save(offer);
        propertyRepository.save(property);
        return this.modelMapper.map(offer, OfferDto.class);
    }


    @Override
    public OfferDto cancelByOwner(Long offerId) throws Exception {
        Offer offer = offerRepository.findById(offerId).orElseThrow();

        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if (Objects.equals(offer.getProperty().getUser().getId(), AUTH.getUserDetails().getId())) {
            if (offer.getStatus() == OfferState.ACCEPTED) {
                offer.setStatus(OfferState.CANCELLED);
                property.setStatus(PropertyState.AVAILABLE);
            } else if (offer.getStatus() == OfferState.PENDING) {
                offer.setStatus(OfferState.CANCELLED);
                var offerWithPending = offerRepository.findByPropertyIdAndStatusIn(property.getId(), Collections.singletonList(OfferState.PENDING));
                if ((long) offerWithPending.size() > 1) {
                    property.setStatus(PropertyState.AVAILABLE);
                }
            }
        } else {
            throw new Exception("You are not authorized");
        }

        offerRepository.save(offer);
        propertyRepository.save(property);
        return this.modelMapper.map(offer, OfferDto.class);
    }

    /**
     * @param propertyId
     * @return
     */
    @Override
    public List<OfferDto> offerGetByPropertyId(Long propertyId) {
        if (AUTH.getUserDetails().getRole() == Role.ADMIN) {
            return offerRepository.findOffersByPropertyId(propertyId).stream().map(offer -> this.modelMapper.map(offer, OfferDto.class)).toList();
        }
        return null;
    }
}
