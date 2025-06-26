package com.example.nasa_hibernate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadFeedbackException extends Exception {
    public BadFeedbackException(String message) {
        super(message);
    }
}
