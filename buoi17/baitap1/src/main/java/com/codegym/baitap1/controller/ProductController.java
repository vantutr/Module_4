package com.codegym.baitap1.controller;

import com.codegym.baitap1.model.Product;
import com.codegym.baitap1.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/products/list";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/products/create";
    }

    @PostMapping("/create")
    public String saveProduct(@ModelAttribute("product") Product product, Model model) {
        productService.save(product);
        model.addAttribute("products", new Product());
        model.addAttribute("message", "Product saved successfully");
        return "/products/create";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "/products/update";
        }
        return "/error_404";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product, Model model) {
        productService.save(product);
        model.addAttribute("products", new Product());
        model.addAttribute("message", "Product updated successfully");
        return "/products/update";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "/products/delete";
        }
        return "/error_404";
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product, Model model) {
        productService.remove(product.getId());
        return "redirect:/products";
    }
}
