package com.waa.backend.services.impl;

import com.waa.backend.domains.Address;
import com.waa.backend.domains.Property;
import com.waa.backend.domains.PropertyState;
import com.waa.backend.dtos.AddressDto;
import com.waa.backend.dtos.PropertyDto;
import com.waa.backend.dtos.PropertyFilterDto;
import com.waa.backend.repositories.PropertyRepository;
import com.waa.backend.request.PropertyRequest;
import com.waa.backend.services.AddressService;
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
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList().stream().map(x -> modelMapper.map(x, PropertyDto.class)).toList();
    }

    @Override
    public PropertyDto create(PropertyRequest propertyRequest) {
        AddressDto address = this.addressService.create(propertyRequest.getAddress());
        Property p = modelMapper.map(propertyRequest, Property.class);
        p.setAddress(modelMapper.map(address, Address.class));
        p.setUser(AUTH.getUserDetails());
        p.setStatus(PropertyState.AVAILABLE);
        return modelMapper.map(repository.save(p), PropertyDto.class);
    }

    @Override
    public PropertyDto update(PropertyRequest propertyRequest, Long id) throws Exception {
        Property p = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        this.updateEntityFromRequest(propertyRequest, p);
        p.setAddress(modelMapper.map(propertyRequest.getAddress(), Address.class));
        p.setUser(AUTH.getUserDetails());
        if (!Objects.equals(p.getUser().getId(), AUTH.getUserDetails().getId()) || propertyRequest.getPropertyState() != PropertyState.AVAILABLE) {
            throw new Exception("Only owner can delete the properties with status 'AVAILABLE'");
        }
        return modelMapper.map(this.repository.save(p), PropertyDto.class);

    }

    @Override
    public boolean delete(Long id) throws Exception {
        Property property = this.repository.findById(id).orElseThrow();
        if (property.getUser().getId() != AUTH.getUserDetails().getId() || property.getStatus() != PropertyState.AVAILABLE) {
            throw new Exception("Only owner can delete the properties with status 'AVAILABLE'");
        }
        this.repository.delete(property);
        return true;
    }
}