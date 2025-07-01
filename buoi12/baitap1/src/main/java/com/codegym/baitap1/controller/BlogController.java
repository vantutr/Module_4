package com.codegym.baitap1.controller;

import com.codegym.baitap1.model.Blog;
import com.codegym.baitap1.model.Category;
import com.codegym.baitap1.service.IBlogService;
import com.codegym.baitap1.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired private IBlogService blogService;
    @Autowired private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping
    public String listBlogs(Model model,
                            @RequestParam("search") Optional<String> search,
                            @PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Blog> blogsPage;
        if (search.isPresent()) {
            blogsPage = blogService.findByTitleContaining(search.get(), pageable);
            model.addAttribute("search", search.get());
        } else {
            blogsPage = blogService.findAll(pageable);
        }
        model.addAttribute("blogsPage", blogsPage);
        return "/blog/list";
    }

    @GetMapping("/category/{id}")
    public String viewCategory(@PathVariable Long id, Model model,
                               @PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return "redirect:/blogs";
        }
        Page<Blog> blogsPage = blogService.findAllByCategoryId(id, pageable);
        model.addAttribute("blogsPage", blogsPage);
        model.addAttribute("categoryName", categoryOptional.get().getName());
        return "/blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "/blog/form";
    }

    @PostMapping("/save")
    public String saveBlog(@ModelAttribute Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Thao tác thành công!");
        return "redirect:/blogs";
    }

    @GetMapping("/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "/blog/view";
        }
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "/blog/form";
        }
        return "redirect:/blogs";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "/blog/delete";
        }
        return "redirect:/blogs";
    }

    @PostMapping("/delete")
    public String deleteBlog(@RequestParam Long id, RedirectAttributes redirect) {
        blogService.remove(id);
        redirect.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/blogs";
    }
}