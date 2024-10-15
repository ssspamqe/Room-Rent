package com.ssspamqe.roomrent.domain.repositories;

import com.ssspamqe.roomrent.domain.entities.security.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
    Optional<UserPassword> findByUserId(long userId);
}
