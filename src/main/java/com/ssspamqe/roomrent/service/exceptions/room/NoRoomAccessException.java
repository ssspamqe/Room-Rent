package com.ssspamqe.roomrent.service.exceptions.room;

import com.ssspamqe.roomrent.service.exceptions.ServiceException;

public class NoRoomAccessException extends ServiceException {
    public NoRoomAccessException(String message) {
        super(message);
    }

    public static NoRoomAccessException toRoomWithId(Long id){
        return new NoRoomAccessException("No access to room with id: " + id);
    }
}
