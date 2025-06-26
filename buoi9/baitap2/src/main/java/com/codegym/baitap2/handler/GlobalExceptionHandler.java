package com.codegym.baitap2.handler;

import com.codegym.baitap2.exception.BookOutOfStockException;
import com.codegym.baitap2.exception.InvalidCodeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookOutOfStockException.class, InvalidCodeException.class})
    public String handleError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
