package com.codegym.baitap2.controller;

import com.codegym.baitap2.exception.BookOutOfStockException;
import com.codegym.baitap2.exception.InvalidCodeException;
import com.codegym.baitap2.model.Book;
import com.codegym.baitap2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public String showBookList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "list";
    }

    @GetMapping("/book/{id}")
    public String showBookDetail(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "detail";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam("bookId") Long bookId, Model model) throws BookOutOfStockException {
        String borrowingCode = bookService.borrowBook(bookId);
        model.addAttribute("code", borrowingCode);
        return "borrow-success";
    }

    @GetMapping("/return")
    public String showReturnForm() {
        return "return-form";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam("borrowingCode") String code, RedirectAttributes redirectAttributes) throws InvalidCodeException {
        bookService.returnBook(code);
        redirectAttributes.addFlashAttribute("message", "Trả sách thành công!");
        return "redirect:/";
    }
}
