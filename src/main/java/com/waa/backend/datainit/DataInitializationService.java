package com.waa.backend.datainit;

import com.waa.backend.domains.Role;
import com.waa.backend.domains.User;
import com.waa.backend.repositories.PropertyRepository;
import com.waa.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class DataInitializationService implements CommandLineRunner {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User admin = User.builder()
                .name("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .isApproved(true).build();
        userRepository.save(admin);
    }
}
