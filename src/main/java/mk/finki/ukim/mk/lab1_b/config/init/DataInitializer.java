/*
package mk.finki.ukim.mk.lab1_b.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.Country;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Role;
import mk.finki.ukim.mk.lab1_b.repository.CountryRepository;
import mk.finki.ukim.mk.lab1_b.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CountryRepository countryRepository;

    public DataInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, CountryRepository countryRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        Country mk = countryRepository.save(new Country("Macedonia", "Europe"));

        userRepository.save(new AppUser(
                "host",
                passwordEncoder.encode("host"),
                Role.ROLE_HOST,
                mk
        ));

        userRepository.save(new AppUser(
                "user",
                passwordEncoder.encode("user"),
                Role.ROLE_USER,
                mk
        ));
    }
}
*/
