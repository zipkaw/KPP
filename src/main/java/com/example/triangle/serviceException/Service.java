package com.example.triangle.serviceException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Invalid data")
public class Service extends RuntimeException {
    public Service(String message){
        super(message);
    }
}

