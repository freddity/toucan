package com.example.toucan.service.handler;

import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice
public class Handler500 /*extends ResponseEntityExceptionHandler*/ {

    /*@ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof SignatureException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return handleExceptionInternal()
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }*/
}