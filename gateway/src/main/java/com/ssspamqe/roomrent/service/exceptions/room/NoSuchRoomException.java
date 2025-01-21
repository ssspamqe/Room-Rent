package com.ssspamqe.roomrent.service.exceptions.room;

import com.ssspamqe.roomrent.service.exceptions.ServiceException;

public class NoSuchRoomException extends ServiceException {
    public NoSuchRoomException(String message) {
        super(message);
    }

    public static NoSuchRoomException withId(Long id) {
        return new NoSuchRoomException("No room found for id: " + id);
    }
}
