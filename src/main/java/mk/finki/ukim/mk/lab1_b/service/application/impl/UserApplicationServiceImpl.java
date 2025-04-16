package mk.finki.ukim.mk.lab1_b.service.application.impl;

import mk.finki.ukim.mk.lab1_b.dto.CreateUserDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayUserDto;
import mk.finki.ukim.mk.lab1_b.dto.LoginUserDto;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.service.application.UserApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.UserDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;
    private final PasswordEncoder passwordEncoder;

    public UserApplicationServiceImpl(UserDomainService userDomainService, PasswordEncoder passwordEncoder) {
        this.userDomainService = userDomainService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());
        AppUser user = userDomainService.register(dto.toAppUser(encodedPassword));
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        AppUser user = userDomainService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return userDomainService.findByUsernameOptional(username)
                .map(DisplayUserDto::from);
    }

    @Override
    public List<AppUser> findAll() {
        return userDomainService.findAll();
    }
}
