package com.waa.backend.util;

import com.waa.backend.domains.User;
import com.waa.backend.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AUTH {
    private static UserRepository userRepository;

    public static User getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            return (User) authentication.getPrincipal();
        } else {
            throw new RuntimeException("User not authenticated");
        }
    }

}
