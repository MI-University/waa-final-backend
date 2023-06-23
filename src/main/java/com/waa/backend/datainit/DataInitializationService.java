package com.waa.backend.datainit;

import com.waa.backend.domains.*;
import com.waa.backend.repositories.AddressRepository;
import com.waa.backend.repositories.PropertyRepository;
import com.waa.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Service
public class DataInitializationService implements CommandLineRunner {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

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
        User user = User.builder()
                .name("User")
                .email("user@gmail.com")
                .password(passwordEncoder.encode("user"))
                .role(Role.CUSTOMER)
                .approved(true).build();
        userRepository.save(user);
        User user2 = User.builder()
                .name("user2")
                .email("user2@gmail.com")
                .password(passwordEncoder.encode("user2"))
                .role(Role.CUSTOMER)
                .approved(true).build();
        userRepository.save(user2);

        Address address = Address.builder().street("1000N 4th Street").city("Fairfield").zip("52557").state("Iowa").build();
        addressRepository.save(address);

        Stream.of(0, 1, 0, 1).forEach(term -> {
            String[] images = {"https://i.ibb.co/Xj9Zq0W/home-6.png", "https://i.ibb.co/qCWHC4s/home-7.png"};
            Property property = Property.builder()
                    .title("Beautiful home near lake")
                    .noOfBedrooms(4)
                    .noOfBathrooms(2.0)
                    .plotSize(3000.0)
                    .area(25000.0)
                    .price(1500000.0)
                    .status(PropertyState.AVAILABLE)
                    .title("Beautiful Home in Fairfield")
                    .description("<h1 class='mb-4'>Features this house owns</h1><p>After selecting the <code>insertDB</code> database, create a table within it. As an example, let’s say you own a factory and want to create a table to store some information about your employees. This table will have the following five columns:</p>")
                    .address(addressRepository.findById((long) 1).get())
                    .user(owner)
                    .images(new ArrayList<>() {{
                        add(images[term]);
                        add("https://i.ibb.co/Xj9Zq0W/home-6.png");
                        add("https://i.ibb.co/qCWHC4s/home-7.png");
                        add("https://i.ibb.co/Xj9Zq0W/home-6.png");
                    }})
                    .build();
            propertyRepository.save(property);
        });

    }
}

