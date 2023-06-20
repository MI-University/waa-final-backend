package com.waa.backend.auth;

import com.waa.backend.domains.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String email;
    private Long userId;
    private String name;
    private Role role;
    private boolean approved;
}
