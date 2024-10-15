package com.ssspamqe.roomrent.domain.repositories;

import com.ssspamqe.roomrent.domain.entities.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> finByUserId(long userId);
}