package com.ssspamqe.roomrent.domain.repositories;

import com.ssspamqe.roomrent.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Optional<User> findByName(String name);
}
