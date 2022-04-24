package com.example.triangle.customAdvice;


import com.example.triangle.handleException.Handle;
import com.example.triangle.myLogger.MyLogger;
import com.example.triangle.response.Response;
import com.example.triangle.serviceException.Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Handle.class)
    public ResponseEntity<Response> handleException(@NotNull Handle e) {
        MyLogger.error("ERROR CODE 400");
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Service.class)
    public ResponseEntity<Response> handleException(@NotNull Service e) {
        MyLogger.error("ERROR CODE 500");
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
