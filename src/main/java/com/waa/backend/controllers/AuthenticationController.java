package com.waa.backend.controllers;

import com.waa.backend.apiresponse.ApiResponse;
import com.waa.backend.request.AuthenticationRequest;
import com.waa.backend.responses.AuthenticationResponse;
import com.waa.backend.services.AuthenticationService;
import com.waa.backend.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Registration successfully done", authenticationService.register(request)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Authenticate successfully done", authenticationService.authenticate(request)));
    }
}
