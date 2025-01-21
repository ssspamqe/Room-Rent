package com.ssspamqe.roomrent.service;

import com.ssspamqe.roomrent.domain.dao.interfaces.UserDAO;
import com.ssspamqe.roomrent.domain.entities.security.UserPassword;
import com.ssspamqe.roomrent.domain.entities.users.User;
import com.ssspamqe.roomrent.domain.repositories.UserPasswordRepository;
import com.ssspamqe.roomrent.service.exceptions.user.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final UserPasswordRepository userPasswordRepository;

    public User getCurrentUser() {
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDAO.getByName(name);
    }

    public UserPassword savePassword(long usedId, String password) {
        var userPassword = UserPassword.builder()
                .userId(usedId)
                .passwordHash(passwordEncoder.encode(password))
                .build();
        return userPasswordRepository.save(userPassword);
    }

    public String getPasswordHashByUserId(long userId) {
        return userPasswordRepository.findByUserId(userId)
                .map(UserPassword::getPasswordHash)
                .orElseThrow(() -> NoSuchUserException.withId(userId));
    }
}
