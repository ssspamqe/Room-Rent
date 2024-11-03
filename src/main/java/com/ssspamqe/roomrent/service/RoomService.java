package com.ssspamqe.roomrent.service;

import com.ssspamqe.roomrent.controllers.room.request.RoomDTO;
import com.ssspamqe.roomrent.domain.dao.interfaces.RoomDAO;
import com.ssspamqe.roomrent.domain.dao.interfaces.SellerDAO;
import com.ssspamqe.roomrent.domain.entities.stuff.Room;
import com.ssspamqe.roomrent.mappers.RoomMapper;
import com.ssspamqe.roomrent.service.exceptions.room.NoRoomAccessException;
import com.ssspamqe.roomrent.service.exceptions.seller.NoSuchSellerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomDAO roomDAO;
    private final SellerDAO sellerDAO;
    private final RoomMapper roomMapper;

    public Room createForSellerWithId(Long sellerId, RoomDTO roomDTO) {
        var seller = sellerDAO.getById(sellerId);
        var room = roomMapper.toEntity(roomDTO, seller);
        return roomDAO.save(room);
    }

    public void deleteForSellerWithId(Long sellerId, Long roomId) {
        if (!sellerDAO.existsById(sellerId)) {
            throw NoSuchSellerException.withId(sellerId);
        }

        var room = roomDAO.getById(roomId);
        if (room.getOwner().getId().equals(sellerId)) {
            room.setDeleted(true);
        } else {
            throw NoRoomAccessException.toRoomWithId(roomId);
        }
    }

    public Room getForSellerWithId(Long sellerId, Long roomId) {
        var room = roomDAO.getById(roomId);
        if (room.getOwner().getId().equals(sellerId)) {
            return room;
        } else {
            throw NoRoomAccessException.toRoomWithId(roomId);
        }
    }

    public Room updateForSellerWithId(Long sellerId, Long roomId, RoomDTO roomDTO) {
        var room = getForSellerWithId(sellerId, roomId);
        roomMapper.update(room, roomDTO);
        return roomDAO.save(room);
    }
}
