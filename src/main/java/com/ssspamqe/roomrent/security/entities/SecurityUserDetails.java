package com.ssspamqe.roomrent.security.entities;

import com.ssspamqe.roomrent.domain.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Builder
public class SecurityUserDetails implements UserDetails {

    private final User user;
    private final String passwordHash;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(
                        role -> new SimpleGrantedAuthority(role.name())
                )
                .toList();
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }
}
