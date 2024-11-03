package com.ssspamqe.roomrent.domain.dao.implementations.hibernate;

import com.ssspamqe.roomrent.domain.dao.interfaces.RoomDAO;
import com.ssspamqe.roomrent.domain.entities.stuff.Room;
import com.ssspamqe.roomrent.domain.repositories.RoomRepository;
import com.ssspamqe.roomrent.service.exceptions.room.NoSuchRoomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HibernateRoomDAO implements RoomDAO {

    private final RoomRepository roomRepository;

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> NoSuchRoomException.withId(id));
    }
}
