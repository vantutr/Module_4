package com.codegym.baitap1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // Sử dụng POST
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class currencyConverterController {

    @GetMapping("/convert")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String currencyConverter(@RequestParam float rate, @RequestParam float usd, Model model) {
        float vnd = usd * rate;
        model.addAttribute("rate", rate);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);
        return "result";
    }
}