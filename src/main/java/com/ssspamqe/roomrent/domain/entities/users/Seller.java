package com.ssspamqe.roomrent.domain.entities.users;

import com.ssspamqe.roomrent.domain.entities.stuff.Announcement;
import com.ssspamqe.roomrent.domain.entities.stuff.Rent;
import com.ssspamqe.roomrent.domain.entities.stuff.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "seller")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "owner")
    private Set<Room> rooms;

    @OneToMany(mappedBy = "seller")
    private Set<Rent> rents;

    @OneToMany(mappedBy = "seller")
    private Set<Announcement> announcements;
}
