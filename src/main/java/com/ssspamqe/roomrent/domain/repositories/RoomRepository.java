package com.ssspamqe.roomrent.domain.repositories;

import com.ssspamqe.roomrent.domain.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Long, Room> {
}
