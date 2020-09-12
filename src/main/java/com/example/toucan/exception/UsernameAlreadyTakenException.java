package com.example.toucan.exception;

public class UsernameAlreadyTakenException extends Exception {

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
