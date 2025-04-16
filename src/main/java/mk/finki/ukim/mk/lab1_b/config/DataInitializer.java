package mk.finki.ukim.mk.lab1_b.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Role;
import mk.finki.ukim.mk.lab1_b.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        userRepository.save(new AppUser(
                "a",
                passwordEncoder.encode("a"),
                Role.ROLE_USER
        ));

        userRepository.save(new AppUser(
                "host",
                passwordEncoder.encode("host"),
                Role.ROLE_HOST
        ));
    }
}
