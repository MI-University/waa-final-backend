package com.waa.backend.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
