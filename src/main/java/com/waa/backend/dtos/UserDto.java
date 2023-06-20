package com.waa.backend.dtos;

import com.waa.backend.domains.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private Long id;
    private String name;
    private Role role;
    private boolean approved;
}
