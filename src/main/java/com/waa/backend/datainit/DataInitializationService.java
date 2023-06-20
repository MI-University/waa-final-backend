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
                .approved(true).build();
        userRepository.save(admin);
        User owner = User.builder()
                .name("owner")
                .email("owner@gmail.com")
                .password(passwordEncoder.encode("owner"))
                .role(Role.OWNER)
                .approved(true).build();
        userRepository.save(owner);
        User owner1 = User.builder()
                .name("owner1")
                .email("owner1@gmail.com")
                .password(passwordEncoder.encode("owner1"))
                .role(Role.OWNER)
                .approved(false).build();
        userRepository.save(owner1);
        User user1 = User.builder()
                .name("user1")
                .email("user1@gmail.com")
                .password(passwordEncoder.encode("user1"))
                .role(Role.CUSTOMER)
                .approved(true).build();
        userRepository.save(user1);
        User user2 = User.builder()
                .name("user2")
                .email("user2@gmail.com")
                .password(passwordEncoder.encode("user2"))
                .role(Role.CUSTOMER)
                .approved(true).build();
        userRepository.save(user2);
    }
}
