package com.waa.backend.controllers;


import com.waa.backend.apiresponse.ApiResponse;
import com.waa.backend.dtos.OfferDto;
import com.waa.backend.dtos.UserDto;
import com.waa.backend.request.OfferRequest;
import com.waa.backend.request.UserRequest;
import com.waa.backend.services.OfferService;
import com.waa.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends GenericCrudControllerImpl<UserRequest, UserDto, Long, UserService> {
    UserService userService;

    public UserController(UserService userService) {
        super(userService, "User");
        this.userService = userService;
    }

    @Autowired
    OfferService offerService;

    @GetMapping("/offers")
    public ResponseEntity<ApiResponse<List<OfferDto>>> checkOfferHistory() {
        List<OfferDto> offers = offerService.findCurrentOffersByCustomerId();
        return ResponseEntity.ok(ApiResponse.success(" Offer retrieved successfully.", offers));
    }

    @PostMapping("/offers")
    public ResponseEntity<ApiResponse<OfferDto>> updateOfferStatus(@RequestBody OfferRequest offerRequest) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer created successfully.", offerService.create(offerRequest)));
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<ApiResponse<OfferDto>> updateOfferStatus(@PathVariable Long id, @RequestBody OfferRequest offerRequest) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer updated successfully.", offerService.update(id, offerRequest)));
    }

    @PutMapping("/offer/{id}/accept-by-owner")
    public ResponseEntity<ApiResponse<OfferDto>> acceptedByOwner(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer accepted successfully.", offerService.acceptBySeller(id)));
    }

    @PutMapping("/offer/{id}/accept-by-customer")
    public ResponseEntity<ApiResponse<OfferDto>> acceptByCustomer(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer accepted successfully.", offerService.acceptByCustomer(id)));
    }


    @PostMapping("/offer/{id}/cancel")
    public ResponseEntity<ApiResponse<OfferDto>> cancel(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ApiResponse.success("Offer canceled successfully.", offerService.cancelByOwner(id)));
    }
}

