package mk.finki.ukim.mk.lab1_b.dto;

import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        Role role
) {

    public AppUser toAppUser(String encodedPassword) {
        return new AppUser(username, encodedPassword, role);
    }
}
