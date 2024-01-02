package com.simlearn.instructormanager.exception;

public class UnknownDBException extends RuntimeException{
    public UnknownDBException (String message) {
        super(message);
    }
}
