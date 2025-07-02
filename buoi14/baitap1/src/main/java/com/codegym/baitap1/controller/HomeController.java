package com.codegym.baitap1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "/access-denied";
    }
}