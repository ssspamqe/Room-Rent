package com.ssspamqe.roomrent.domain.dao.interfaces;

import com.ssspamqe.roomrent.domain.entities.stuff.Room;

public interface RoomDAO {
    Room save(Room room);

    Room getById(Long id);
}
