package com.ssspamqe.roomrent.service.exceptions.user;

public class NoSuchUserException extends UserServiceException {
    public NoSuchUserException(String message) {
        super(message);
    }

    public static NoSuchUserException withName(String name) {
        return new NoSuchUserException("No such user with name: " + name);
    }

    public static NoSuchUserException withEmail(String email) {
        return new NoSuchUserException("No such user with email: " + email);
    }

    public static NoSuchUserException withId(long id) {
        return new NoSuchUserException("No such user with id: " + id);
    }
}
