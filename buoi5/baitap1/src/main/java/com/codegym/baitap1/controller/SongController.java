package com.codegym.baitap1.controller;

import com.codegym.baitap1.model.Song;
import com.codegym.baitap1.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final String uploadPath = "C:/music-files/";

    @Autowired
    private ISongService songService;

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("create", "song", new Song());
    }

    @PostMapping("/save")
    public String saveSong(@ModelAttribute Song song, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(uploadPath + fileName));
                song.setFilePath(fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        if (song != null) {
            model.addAttribute("song", song);
            return "edit";
        }
        return "redirect:/songs";
    }

    @PostMapping("/update")
    public String updateSong(@ModelAttribute Song song, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // Xử lý upload file mới nếu có
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(uploadPath + fileName));
                song.setFilePath(fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // Nếu không upload file mới, filePath cũ sẽ được giữ lại nhờ input hidden trong form
        songService.save(song);
        return "redirect:/songs";
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.remove(id);
        return "redirect:/songs";
    }
}