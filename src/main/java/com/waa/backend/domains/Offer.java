package com.waa.backend.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate date;
    LocalTime time;
    Double offerAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    Property property;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value="offer-customer")
    User user;
    @Enumerated(EnumType.STRING)
    OfferState status;
}
