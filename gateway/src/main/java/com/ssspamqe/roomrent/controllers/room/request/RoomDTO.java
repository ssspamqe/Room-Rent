package com.ssspamqe.roomrent.controllers.room.request;

public record RoomDTO(
        String name,
        String description,
        EarthPosition position
) {

}
