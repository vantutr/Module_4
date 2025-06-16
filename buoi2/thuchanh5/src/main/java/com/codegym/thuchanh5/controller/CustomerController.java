package com.codegym.thuchanh5.controller;

import com.codegym.thuchanh5.model.Customer;
import com.codegym.thuchanh5.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    // Tiêm (Inject) CustomerService bằng @Autowired
    @Autowired
    private CustomerService customerService;

    // Xử lý request GET đến "/customers" để hiển thị danh sách
    @GetMapping()
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    // Xử lý request GET đến "/customers/info" để hiển thị chi tiết
    @GetMapping("/{id}")
    public ModelAndView showInformation(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("info");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
}
