package com.ssspamqe.roomrent.service.exceptions.seller;

import com.ssspamqe.roomrent.service.exceptions.ServiceException;

public class NoSuchSellerException extends ServiceException {
    public NoSuchSellerException(String message) {
        super(message);
    }

    public static NoSuchSellerException forUserId(Long userId) {
        return new NoSuchSellerException("No seller found for user id: " + userId);
    }
}
