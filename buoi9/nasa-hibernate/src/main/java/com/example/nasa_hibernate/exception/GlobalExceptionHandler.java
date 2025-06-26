package com.example.nasa_hibernate.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadFeedbackException.class)
    public String handleBadFeedbackException(BadFeedbackException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-bad-feedback";
    }
}
