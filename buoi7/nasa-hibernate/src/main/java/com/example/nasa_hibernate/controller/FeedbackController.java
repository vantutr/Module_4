package com.example.nasa_hibernate.controller;

import com.example.nasa_hibernate.model.Feedback;
import com.example.nasa_hibernate.repository.FeedbackRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Feedback> feedbackPage = repo.findByDate(new Date(), pageable);

        model.addAttribute("feedback", new Feedback());
        model.addAttribute("apiKey", apiKey);
        model.addAttribute("feedbackPage", feedbackPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", feedbackPage.getTotalPages());
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
