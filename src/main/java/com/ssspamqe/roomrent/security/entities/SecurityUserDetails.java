package com.ssspamqe.roomrent.security.entities;

import com.ssspamqe.roomrent.domain.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Log4j2
@Builder
public class SecurityUserDetails implements UserDetails {

    private final User user;
    private final String passwordHash;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var res = user.getRoles().stream()
                .map(
                        role -> new SimpleGrantedAuthority(role.name())
                )
                .toList();
        log.info("returning roles: {}", res);
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
