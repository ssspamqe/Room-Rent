package com.ssspamqe.roomrent.domain.entities.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_passwords")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPassword {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "password_hash")
    String passwordHash;

}
