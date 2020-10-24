package com.example.toucan.exception;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(String msg) {
        super(msg);
    }

    public NoteNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
