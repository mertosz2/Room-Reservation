package com.example.utccroomreservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoomsAlreadyBookedException extends RuntimeException {
    public RoomsAlreadyBookedException(String msg) {
        super(msg);
    }
}
