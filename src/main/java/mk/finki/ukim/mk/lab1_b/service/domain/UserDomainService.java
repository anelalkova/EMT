package mk.finki.ukim.mk.lab1_b.service.domain;

import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserDomainService extends UserDetailsService {

    AppUser register(AppUser user);

    AppUser login(String username, String password);

    AppUser findByUsername(String username);

    Optional<AppUser> findById(Long id);

    List<AppUser> findAll();

    Optional<AppUser> findByUsernameOptional(String username);
}
