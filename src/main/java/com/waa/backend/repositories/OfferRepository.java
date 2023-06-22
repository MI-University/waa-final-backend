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
    @Query("SELECT o FROM Offer o WHERE o.user.id = :userId")
    List<Offer> findOffersByUserId(@Param("userId") Long userId);

    @Query("SELECT o FROM Offer o WHERE o.property.user.id = :userId")
    List<Offer> findOffersByOwnerId(@Param("userId") Long userId);


    @Query("SELECT o FROM Offer o INNER JOIN o.property p WHERE o.status IN  :offerStates AND p.id = :propertyId")
    List<Offer> findByPropertyIdAndStatusIn(@Param("propertyId") Long propertyId, @Param("offerStates") List<OfferState> offerStates);

    @Query("SELECT o FROM Offer o WHERE o.property.id = :propertyId")
    List<Offer> findOffersByPropertyId(@Param("propertyId") Long propertyId);

    @Query("SELECT o FROM Offer o WHERE o.property.id = :propertyId and o.property.user.id = :userId")
    List<Offer> getByPropertyIdAndUserId(@Param("propertyId") Long propertyId, @Param("userId") Long userId);


    @Query("Update Offer offer set offer.status = 'CANCELLED' where offer.id != :offerId and offer.property.id = :propertyId")
    List<Offer> setALlOfferCancelledNotId(@Param("offerId") Long offerId, @Param("propertyId") Long propertyId);
}

