package com.waa.backend.repositories;

import com.waa.backend.domains.Offer;
import com.waa.backend.domains.OfferState;
import com.waa.backend.domains.User;
import com.waa.backend.dtos.OfferDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findOffersByUserId(Long id);

    @Query("SELECT o FROM Offer o INNER JOIN o.property p WHERE o.status IN  :offerStates AND p.id = :propertyId")
    List<Offer> findByPropertyIdAndStatusIn(Long propertyId, List<OfferState> offerStates);

    @Query("SELECT o FROM Offer o WHERE o.property.id = :propertyId and o.property.user.id =: userId")
    List<OfferDto> getByPropertyIdAndUserId(Long propertyId, Long userId);
}

