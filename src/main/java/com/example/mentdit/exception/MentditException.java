package com.example.mentdit.exception;


public class MentditException extends RuntimeException {
    public MentditException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
