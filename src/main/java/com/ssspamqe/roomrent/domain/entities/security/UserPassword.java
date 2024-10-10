package com.ssspamqe.roomrent.domain.entities.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_passowords")
@Getter
@Setter
public class UserPassword {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "password_hash")
    String passwordHash;

}
