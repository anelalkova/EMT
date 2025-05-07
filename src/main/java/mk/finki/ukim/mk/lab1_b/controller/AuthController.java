package mk.finki.ukim.mk.lab1_b.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import mk.finki.ukim.mk.lab1_b.dto.CreateUserDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayUserDto;
import mk.finki.ukim.mk.lab1_b.dto.LoginResponseDto;
import mk.finki.ukim.mk.lab1_b.dto.LoginUserDto;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab1_b.service.application.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserApplicationService userApplicationService;

    public AuthController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user with the provided username and password.")
    public ResponseEntity<DisplayUserDto> register(@RequestBody @Valid CreateUserDto createUserDto) {
        if (createUserDto == null || createUserDto.username() == null || createUserDto.password() == null) {
            return ResponseEntity.badRequest().build();
        }
        return userApplicationService.register(createUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/logout")
    @Operation(summary = "Logout user", description = "Logs out the current logged-in user.")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Fetches a list of all users.")
    public List<DisplayUserDto> getAllUsers() {
        return userApplicationService.findAll();
    }
}
