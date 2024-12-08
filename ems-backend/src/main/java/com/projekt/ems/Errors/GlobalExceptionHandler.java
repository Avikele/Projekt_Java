package com.projekt.ems.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> EmailAlreadyExistsException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }




}
