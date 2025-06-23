package com.example.nasa_hibernate.controller;

import com.example.nasa_hibernate.model.Feedback;
import com.example.nasa_hibernate.repository.FeedbackRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;
import java.util.Date;

@Controller
public class FeedbackController {
    @Value("${nasa.api.key}")
    private String apiKey;

    private final FeedbackRepository repo;

    public FeedbackController(FeedbackRepository repo) {
        this.repo = repo;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("apiKey", apiKey);
        model.addAttribute("feedbacks", repo.findByDate(new Date()));
        return "index";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Feedback feedback) {
        feedback.setDate(new Date());
        feedback.setLikes(0);
        repo.save(feedback);
        return "redirect:/";
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable Long id, HttpServletRequest request) {
        Feedback feedback = repo.findById(id).orElse(null);
        if (feedback != null) {
            feedback.setLikes(feedback.getLikes() + 1);
            repo.save(feedback);
        }
        return "redirect:/";
    }
}
