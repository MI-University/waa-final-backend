package com.waa.backend.domains;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Property extends BaseDomain{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer noOfBedrooms;
    Double noOfBathrooms;
    Double plotSize;
    Double price;
    Double area;
    @NotEmpty
    String title;

    @NotEmpty
    @Column(columnDefinition = "text")
    String description;

    @OneToOne(fetch = FetchType.LAZY)
    Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    List<Offer> offers;

    @Enumerated(EnumType.STRING)
    PropertyState status;

    @ElementCollection
    @CollectionTable(name = "images")
    @Column(name = "image")
    private List<String> images;
}
