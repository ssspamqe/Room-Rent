package com.ssspamqe.roomrent.domain.dao.interfaces;

import com.ssspamqe.roomrent.domain.entities.users.User;

public interface UserDAO {
    User save(User user);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    User getById(Long id);


}
