package mk.finki.ukim.mk.lab1_b.service.application;


import mk.finki.ukim.mk.lab1_b.dto.CreateUserDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayUserDto;
import mk.finki.ukim.mk.lab1_b.dto.LoginUserDto;
import mk.finki.ukim.mk.lab1_b.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    List<AppUser> findAll();
}

