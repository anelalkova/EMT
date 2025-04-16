package mk.finki.ukim.mk.lab1_b.dto;

import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Role;

public record DisplayUserDto(String username, String password, Role role) {

    public static DisplayUserDto from(AppUser user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    public AppUser toAppUser() {
        // Password is not available in Display DTO, assume null
        return new AppUser(username, password, role);
    }
}
