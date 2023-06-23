package com.waa.backend.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double offerAmount;

    @Column(columnDefinition = "TEXT")
    String details;

    @ManyToOne(fetch = FetchType.LAZY)
    Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "offer-customer")
    User user;

    @Enumerated(EnumType.STRING)
    OfferState status;

}
