package com.ssspamqe.roomrent.controllers.room;

import com.ssspamqe.roomrent.controllers.global.RoomRentResponse;
import com.ssspamqe.roomrent.controllers.room.request.RoomDTO;
import com.ssspamqe.roomrent.domain.entities.stuff.Room;
import com.ssspamqe.roomrent.service.RoomService;
import com.ssspamqe.roomrent.service.SecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;
    private final SecurityUserService securityUserService;

    @PostMapping
    RoomRentResponse<Room> createRoom(@RequestBody RoomDTO room) {
        var currentUserId = securityUserService.getCurrentUser().getId();
        var createdRoom = roomService.createForSellerWithId(currentUserId, room);
        return RoomRentResponse.of(createdRoom);
    }

    @GetMapping("/{roomId}")
    RoomRentResponse<Room> getRoomById(@PathVariable Long roomId) {
        var currentUserId = securityUserService.getCurrentUser().getId();
        var room = roomService.getForSellerWithId(currentUserId, roomId);
        return RoomRentResponse.of(room);
    }

    @DeleteMapping("/{roomId}")
    RoomRentResponse<Void> deleteRoomById(@PathVariable Long roomId) {
        var currentUserId = securityUserService.getCurrentUser().getId();
        roomService.deleteForSellerWithId(currentUserId, roomId);
        return RoomRentResponse.ok();
    }

    @PutMapping("/{roomId}")
    RoomRentResponse<Room> updateRoomById(@PathVariable Long roomId, @RequestBody RoomDTO room) {
        var currentUserId = securityUserService.getCurrentUser().getId();
        var updatedRoom = roomService.updateForSellerWithId(currentUserId, roomId, room);
        return RoomRentResponse.of(updatedRoom);
    }
}
