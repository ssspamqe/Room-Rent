package com.ssspamqe.roomrent.service;

import com.ssspamqe.roomrent.domain.dao.interfaces.UserDAO;
import com.ssspamqe.roomrent.domain.entities.users.User;
import com.ssspamqe.roomrent.service.exceptions.user.SuchUserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public User create(User user) {
        validateNewUserUniqueness(user);
        return userDAO.save(user);
    }

    private void validateNewUserUniqueness(User user) {
        var name = user.getName();
        if (userDAO.existsByName(name)) {
            throw SuchUserAlreadyExistsException.withName(name);
        }

        var email = user.getEmail();
        if (userDAO.existsByEmail(email)) {
            throw SuchUserAlreadyExistsException.withEmail(email);
        }
    }

}
