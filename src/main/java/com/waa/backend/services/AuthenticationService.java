package com.waa.backend.services;


import com.waa.backend.request.AuthenticationRequest;
import com.waa.backend.request.RegisterRequest;
import com.waa.backend.responses.AuthenticationResponse;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
