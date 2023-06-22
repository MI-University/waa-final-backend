package com.waa.backend.repositories;

import com.waa.backend.domains.Offer;
import com.waa.backend.domains.OfferState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o WHERE o.user.id =: userId")
    List<Offer> findOffersByUserId(Long id);

    @Query("SELECT o FROM Offer o WHERE o.property.user.id =: userId")
    List<Offer> findOffersByOwnerId(Long id);

    @Query("SELECT o FROM Offer o INNER JOIN o.property p WHERE o.status IN  :offerStates AND p.id = :propertyId")
    List<Offer> findByPropertyIdAndStatusIn(Long propertyId, List<OfferState> offerStates);

    @Query("SELECT o FROM Offer o WHERE o.property.id = :propertyId")
    List<Offer> findOffersByPropertyId(Long propertyId);

    @Query("SELECT o FROM Offer o WHERE o.property.id = :propertyId and o.property.user.id = :userId")
    List<Offer> getByPropertyIdAndUserId(@Param("propertyId") Long propertyId, @Param("userId") Long userId);

}

