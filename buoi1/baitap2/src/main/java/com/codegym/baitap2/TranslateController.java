package com.codegym.baitap2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TranslateController {

    private static Map<String, String> dictionary = new HashMap<>();
    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("goodbye", "tạm biệt");
        dictionary.put("name", "tên");
        dictionary.put("age", "tuổi");
        dictionary.put("book", "sách");
    }

    @GetMapping("/index")
    public String showForm() {
        return "index";
    }

    @PostMapping("/index")
    public String translate(@RequestParam String english, Model model) {
        String vietnamese = dictionary.get(english.toLowerCase().trim());
        model.addAttribute("english", english);
        model.addAttribute("vietnamese", vietnamese);
        return "result";
    }

}
