package mk.finki.ukim.mk.lab1_b.service.application.impl;

import mk.finki.ukim.mk.lab1_b.dto.*;
import mk.finki.ukim.mk.lab1_b.helpers.JwtHelper;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.Country;
import mk.finki.ukim.mk.lab1_b.service.application.UserApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.CountryDomainService;
import mk.finki.ukim.mk.lab1_b.service.domain.UserDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;
    private final PasswordEncoder passwordEncoder;
    private final CountryDomainService countryDomainService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserDomainService userDomainService, PasswordEncoder passwordEncoder, CountryDomainService countryDomainService, JwtHelper jwtHelper) {
        this.userDomainService = userDomainService;
        this.passwordEncoder = passwordEncoder;
        this.countryDomainService = countryDomainService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto dto) {
        //String encodedPassword = passwordEncoder.encode(dto.password());
        Country country = countryDomainService.findById(dto.countryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid country ID"));

        AppUser user = userDomainService.register(dto.toAppUser(dto.password(), country));

        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        AppUser user = userDomainService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return userDomainService.findByUsernameOptional(username)
                .map(DisplayUserDto::from);
    }

    @Override
    public List<DisplayUserDto> findAll() {
        return userDomainService.findAll().stream().map(DisplayUserDto::from).toList();
    }
}
