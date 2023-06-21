package com.waa.backend.repositories;

import com.waa.backend.domains.Offer;
import com.waa.backend.domains.OfferState;
import com.waa.backend.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByPropertyUser(User user);
    List<Offer> findOfferByUser(User user);
    List<Offer> findOffersByStatus(OfferState status);
    @Query("SELECT offer FROM Offer offer INNER JOIN offer.property property INNER JOIN property.user owner WHERE owner.id = :ownerId")
    List<Offer> findByOwnerId(Long ownerId);
    List<Offer> findByPropertyUserAndStatusIn(User user, List<OfferState> offerStates);
    @Query("SELECT o FROM Offer o INNER JOIN o.property p WHERE o.status IN  :offerStates AND p.id = :propertyId")
    List<Offer> findByPropertyIdAndStatusIn(Long propertyId, List<OfferState> offerStates);
}

