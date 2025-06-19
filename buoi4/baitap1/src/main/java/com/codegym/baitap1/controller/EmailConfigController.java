package com.codegym.baitap1.controller;

import com.codegym.baitap1.model.EmailConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/emailConfig")
public class EmailConfigController {

    private EmailConfig currentConfig = new EmailConfig("English", 25, true, "Thor\nKing, Asgard");

    @GetMapping("/settings")
    public ModelAndView showSettingsForm() {
        ModelAndView modelAndView = new ModelAndView("index");

        List<String> languages = Arrays.asList("English", "Vietnamese", "Japanese", "Chinese");
        List<Integer> pageSizes = Arrays.asList(5, 10, 15, 25, 50, 100);

        modelAndView.addObject("emailConfig", currentConfig);
        modelAndView.addObject("languages", languages);
        modelAndView.addObject("pageSizes", pageSizes);
        return modelAndView;
    }

    @PostMapping("settings/update")
    public String saveSettings(@ModelAttribute("emailConfig") EmailConfig updatedConfig, Model model) {
        this.currentConfig = updatedConfig;

        model.addAttribute("message", "Settings updated successfully!");
        model.addAttribute("updatedConfig", currentConfig);

        return "result";
    }


}
