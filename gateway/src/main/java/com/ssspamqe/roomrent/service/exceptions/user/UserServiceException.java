package com.ssspamqe.roomrent.service.exceptions.user;

import com.ssspamqe.roomrent.service.exceptions.ServiceException;

public class UserServiceException extends ServiceException {
    public UserServiceException(String message) {
        super(message);
    }
}
