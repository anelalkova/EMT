package mk.finki.ukim.mk.lab1_b.dto;

import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.Country;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        Role role,
        Long countryId
) {

    public AppUser toAppUser(String encodedPassword, Country country) {
        return new AppUser(username, encodedPassword, role, country);
    }
}
