package com.codegym.baitap1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {

    @GetMapping("/")
    public String sandwich() {
        return "home";
    }

    @PostMapping("/{condiment}")
    public String listSandwich(@RequestParam("condiment") String condiment, Model model) {
        model.addAttribute("condiment", condiment);
        return "result";
    }
}
