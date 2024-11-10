package com.svprofi.trackit.exception;

public class MailNotFoundException extends RuntimeException {

    public MailNotFoundException(String message) {
        super(message);
    }
}
