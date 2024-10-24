package com.ssspamqe.roomrent.service.exceptions.user;

public class SuchUserAlreadyExistsException extends UserServiceException {
    public SuchUserAlreadyExistsException(String message) {
        super(message);
    }

    public static SuchUserAlreadyExistsException withEmail(String email) {
        return new SuchUserAlreadyExistsException("User with such email:" + email + " already exists");
    }

    public static SuchUserAlreadyExistsException withName(String name) {
        return new SuchUserAlreadyExistsException("User with such name: " + name + " already exists");
    }
}
