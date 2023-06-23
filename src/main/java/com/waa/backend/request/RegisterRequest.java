package com.waa.backend.request;


import com.waa.backend.domains.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    private Role role;
}
