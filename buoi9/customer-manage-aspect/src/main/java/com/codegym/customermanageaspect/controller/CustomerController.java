package com.codegym.customermanageaspect.controller;

import com.codegym.customermanageaspect.model.Customer;
import com.codegym.customermanageaspect.service.ICustomerService;
import com.codegym.customermanageaspect.service.exception.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("customer/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customer/info");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            customerService.save(customer);
            return "redirect:/customers";
        } catch (DuplicateEmailException e) {
            model.addAttribute("errorMessage", "Lỗi: " + e.getMessage());
            // Trả về form với dữ liệu người dùng đã nhập và thông báo lỗi
            return "customer/info";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/info";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.remove(id);
        return "redirect:/customers";
    }
}
