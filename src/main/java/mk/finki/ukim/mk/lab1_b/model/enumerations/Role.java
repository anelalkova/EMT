package mk.finki.ukim.mk.lab1_b.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}
