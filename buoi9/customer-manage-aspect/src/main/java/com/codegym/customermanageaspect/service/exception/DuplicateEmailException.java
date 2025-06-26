package com.codegym.customermanageaspect.service.exception;

public class DuplicateEmailException extends Exception {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
