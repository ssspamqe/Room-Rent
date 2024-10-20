package com.ssspamqe.roomrent.service;

import com.ssspamqe.roomrent.domain.entities.users.User;
import com.ssspamqe.roomrent.domain.repositories.UserRepository;
import com.ssspamqe.roomrent.service.exceptions.user.NoSuchUserException;
import com.ssspamqe.roomrent.service.exceptions.user.SuchUserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        validateNewUserUniqueness(user);
        return save(user);
    }

    private void validateNewUserUniqueness(User user) {
        var name = user.getName();
        if (userRepository.existsByName(name)) {
            throw SuchUserAlreadyExistsException.withName(name);
        }

        var email = user.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw SuchUserAlreadyExistsException.withEmail(email);
        }
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    public User getByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> NoSuchUserException.withName(name));
    }

}
