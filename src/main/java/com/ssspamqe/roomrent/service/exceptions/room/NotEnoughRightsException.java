package com.ssspamqe.roomrent.service.exceptions.room;

import com.ssspamqe.roomrent.service.exceptions.ServiceException;

public class NotEnoughRightsException extends ServiceException {
    public NotEnoughRightsException(String message) {
        super(message);
    }
}
