package com.ssspamqe.roomrent.domain.entities.stuff;

import com.ssspamqe.roomrent.domain.entities.users.Seller;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.Set;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller owner;

    @Column(name = "position")
    private Point position;

    @OneToMany
    @JoinColumn(name = "room_id")
    private Set<Rent> rents;

    @OneToMany(mappedBy = "room")
    private Set<Announcement> announcements;

    @Column(name = "deleted")
    private boolean deleted;
}
