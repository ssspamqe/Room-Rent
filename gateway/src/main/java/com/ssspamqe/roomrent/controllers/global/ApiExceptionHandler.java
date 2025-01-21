package com.ssspamqe.roomrent.controllers.global;

import com.ssspamqe.roomrent.service.exceptions.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<RoomRentResponse<Void>> handleServiceException(ServiceException e) {
        return ResponseEntity
                .status(400)
                .body(RoomRentResponse.error(e));
    }

}
