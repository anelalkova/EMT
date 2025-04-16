package mk.finki.ukim.mk.lab1_b.service.domain.impl;

import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab1_b.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab1_b.repository.UserRepository;
import mk.finki.ukim.mk.lab1_b.service.domain.UserDomainService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDomainServiceImpl(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser register(AppUser appUser) {
        if (userRepository.findByUsername(appUser.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        AppUser user = new AppUser(appUser.getUsername(), passwordEncoder.encode(appUser.getPassword()), appUser.getRole());
        return userRepository.save(user);
    }

    @Override
    public AppUser login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        AppUser user = userRepository.findByUsername(username).orElseThrow(InvalidUserCredentialsException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserCredentialsException();
        }
        return user;
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> findByUsernameOptional(String username) {
        return userRepository.findByUsername(username);
    }
}
