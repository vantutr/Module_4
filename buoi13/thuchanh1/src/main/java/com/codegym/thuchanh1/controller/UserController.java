package com.codegym.thuchanh1.controller;

import com.codegym.thuchanh1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/doLogin")
    public ModelAndView login(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("/dashboard");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}