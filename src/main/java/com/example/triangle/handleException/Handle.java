package com.example.triangle.handleException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid data")
public class Handle extends RuntimeException {
    public Handle(String message){
        super(message);
    }
}
