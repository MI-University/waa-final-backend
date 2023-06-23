package com.waa.backend.services.impl;


import com.waa.backend.config.JwtService;
import com.waa.backend.domains.Role;
import com.waa.backend.domains.User;
import com.waa.backend.repositories.UserRepository;
import com.waa.backend.request.AuthenticationRequest;
import com.waa.backend.request.RegisterRequest;
import com.waa.backend.responses.AuthenticationResponse;
import com.waa.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .approved(request.getRole() == Role.CUSTOMER)
                .build();

        User user = userRepository.save(newUser);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .userId(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .approved(user.getapproved())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .userId(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .approved(user.getapproved())
                .build();
    }
}
