package com.codegym.baitap2.controller;

import com.codegym.baitap2.model.Song;
import com.codegym.baitap2.service.ISongService;
import com.codegym.baitap2.service.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService songService;

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        if (song != null) {
            model.addAttribute("song", song);
            return "form";
        }
        return "redirect:/songs";
    }

    @PostMapping("/save")
    public String saveSong(@Valid @ModelAttribute("song") Song song,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        songService.save(song);
        String message = song.getId() != null ? "Cập nhật bài hát thành công!" : "Thêm mới bài hát thành công!";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/songs";
    }
}
