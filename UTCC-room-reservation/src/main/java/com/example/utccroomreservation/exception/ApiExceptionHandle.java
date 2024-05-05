package com.example.utccroomreservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandle extends ResponseStatusExceptionHandler {

    @ExceptionHandler(value = {RoomNotFoundException.class})
    public ResponseEntity<Object> RoomNotFoundException(RoomNotFoundException e){
        ExceptionResponse exceptionHandler = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exceptionHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RoomsAlreadyBookedException.class})
    public ResponseEntity<Object> RoomsAlreadyBookedException(RoomsAlreadyBookedException e){
        ExceptionResponse exceptionHandler = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exceptionHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {StudentNotFoundException.class})
    public ResponseEntity<Object> StudentNotFoundException(StudentNotFoundException e){
        ExceptionResponse exceptionHandler = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exceptionHandler, HttpStatus.BAD_REQUEST);
    }
}
