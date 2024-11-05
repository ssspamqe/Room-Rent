package com.ssspamqe.roomrent.domain.dao.implementations.hibernate;

import com.ssspamqe.roomrent.domain.dao.interfaces.UserDAO;
import com.ssspamqe.roomrent.domain.entities.users.User;
import com.ssspamqe.roomrent.domain.repositories.UserRepository;
import com.ssspamqe.roomrent.service.exceptions.user.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HibernateUserDAO implements UserDAO {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> NoSuchUserException.withId(id));
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> NoSuchUserException.withName(name));
    }
}
