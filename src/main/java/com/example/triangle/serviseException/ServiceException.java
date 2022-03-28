package com.example.triangle.serviseException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Invalid data")
public class ServiceException  extends RuntimeException {
    public ServiceException(String message){
        super(message);
    }
}