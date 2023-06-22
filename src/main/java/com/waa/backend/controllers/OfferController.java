package com.waa.backend.controllers;


import com.waa.backend.apiresponse.ApiResponse;
import com.waa.backend.dtos.OfferDto;
import com.waa.backend.request.OfferFilterRequest;
import com.waa.backend.request.OfferRequest;
import com.waa.backend.services.OfferService;
import com.waa.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
public class OfferController extends GenericCrudControllerImpl<OfferRequest, OfferDto, Long, OfferService> {
    UserService userService;

    public OfferController(OfferService offerService, UserService userService) {
        super(offerService, "Offer");
        this.userService = userService;
    }

    @Autowired
    OfferService offerService;

    @PutMapping("/{id}/accept-by-owner")
    public ResponseEntity<ApiResponse<OfferDto>> acceptedByOwner(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer accepted successfully.", offerService.acceptBySeller(id)));
    }

    @PutMapping("/{id}/accept-by-customer")
    public ResponseEntity<ApiResponse<OfferDto>> acceptByCustomer(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer accepted successfully.", offerService.acceptByCustomer(id)));
    }


    @PostMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<OfferDto>> cancel(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer canceled successfully.", offerService.cancelByOwner(id)));
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<ApiResponse<List<OfferDto>>> checkOfferHistoryByPropertyId(@PathVariable Long id) {
        List<OfferDto> offers = offerService.offerGetByPropertyId(id);
        return ResponseEntity.ok(ApiResponse.success(" Offer retrieved successfully.", offers));
    }
}

