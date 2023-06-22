package com.waa.backend.services.impl;

import com.waa.backend.domains.Address;
import com.waa.backend.domains.OfferState;
import com.waa.backend.domains.Property;
import com.waa.backend.domains.PropertyState;
import com.waa.backend.dtos.AddressDto;
import com.waa.backend.dtos.OfferDto;
import com.waa.backend.dtos.PropertyDto;
import com.waa.backend.dtos.PropertyFilterDto;
import com.waa.backend.repositories.PropertyRepository;
import com.waa.backend.request.PropertyRequest;
import com.waa.backend.services.AddressService;
import com.waa.backend.services.OfferService;
import com.waa.backend.services.PropertyService;
import com.waa.backend.util.AUTH;
import com.waa.backend.util.ModelMapperHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PropertyServiceImpl extends GenericCrudServiceImpl<Property, PropertyRequest, PropertyDto, Long> implements PropertyService {
    private final PropertyRepository repository;
    @Autowired
    private OfferService offerService;

    @Autowired
    private AddressService addressService;
    private final EntityManager em;
    @Autowired
    ModelMapper modelMapper;

    public PropertyServiceImpl(PropertyRepository repository, ModelMapperHelper<Property, PropertyDto> modelMapperHelper, EntityManager em) {
        super(repository, modelMapperHelper, Property.class, PropertyDto.class);
        this.repository = repository;
        this.em = em;
    }

    @Override
    public List<PropertyDto> getAll(PropertyFilterDto filterDataDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Property> cq = cb.createQuery(Property.class);

        Root<Property> property = cq.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filterDataDto.getCity() != null && !filterDataDto.getCity().equals("")) {
            predicates.add(cb.like(property.get("address").get("city"), "%" + filterDataDto.getCity() + "%"));
        }
        if (filterDataDto.getMaxPrice() != null && filterDataDto.getMaxPrice() != 0) {
            predicates.add(cb.lessThanOrEqualTo(property.get("price"), filterDataDto.getMaxPrice()));
        }
        if (filterDataDto.getMinPrice() != null && filterDataDto.getMinPrice() != 0) {
            predicates.add(cb.greaterThanOrEqualTo(property.get("price"), filterDataDto.getMinPrice()));
        }
        if (filterDataDto.getNoOfBedrooms() != 0) {
            predicates.add(cb.greaterThanOrEqualTo(property.get("noOfBedrooms"), filterDataDto.getNoOfBedrooms()));
        }
        if (filterDataDto.isCAuth()) {
            predicates.add(cb.equal(property.get("user").get("id"), AUTH.getUserDetails().getId()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList().stream().map(x -> modelMapper.map(x, PropertyDto.class)).toList();
    }

    /**
     * @param propertyId
     * @param offerId
     * @return
     */
    @Override
    public PropertyDto soldProperty(Long propertyId, Long offerId) throws ChangeSetPersister.NotFoundException {
        Property existingProperty = repository.findById(propertyId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        OfferDto offerDto = this.offerService.getById(offerId);
        if (
                existingProperty.getStatus() == PropertyState.CONTINGENT
                        && offerDto.getStatus() == OfferState.ACCEPTED
                        && Objects.equals(offerDto.getProperty().getId(), offerId)
                        && Objects.equals(existingProperty.getUser().getId(), AUTH.getUserDetails().getId())
        ) {
            existingProperty.setStatus(PropertyState.SOLD);
//            this.offerService.setAllOfferCancelledNotId(OfferState.CANCELLED, offerId, propertyId);
        }
        return this.modelMapper.map(this.repository.save(existingProperty), PropertyDto.class);
    }

    @Override
    public PropertyDto create(PropertyRequest propertyRequest) throws Exception {
        AddressDto address = this.addressService.create(propertyRequest.getAddress());
        Property p = modelMapper.map(propertyRequest, Property.class);
        p.setAddress(modelMapper.map(address, Address.class));
        p.setUser(AUTH.getUserDetails());
        p.setStatus(PropertyState.AVAILABLE);
        return modelMapper.map(repository.save(p), PropertyDto.class);
    }

    @Override
    public PropertyDto update(PropertyRequest propertyRequest, Long id) throws Exception {

        // Fetch the existing Property entity from the database
        Property existingProperty = repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Map the fields from PropertyDto to Property entity
        modelMapper.map(propertyRequest, existingProperty);

        // Save the updated Property entity back to the database
        existingProperty.setAddress(modelMapper.map(propertyRequest.getAddress(), Address.class));
        if (!Objects.equals(existingProperty.getUser().getId(), AUTH.getUserDetails().getId())) {
            throw new Exception("Only owner can delete the properties");
        }

        return modelMapper.map(this.repository.save(existingProperty), PropertyDto.class);

    }

    @Override
    public boolean delete(Long id) throws Exception {
        Property property = this.repository.findById(id).orElseThrow();
        if (!Objects.equals(property.getUser().getId(), AUTH.getUserDetails().getId()) || property.getStatus() != PropertyState.AVAILABLE) {
            throw new Exception("Only owner can delete the properties with status 'AVAILABLE'");
        }
        this.repository.delete(property);
        return true;
    }


}