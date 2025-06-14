package com.codegym.thuchanh2.controller;

import com.codegym.thuchanh2.model.Customer;
import com.codegym.thuchanh2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    // Tiêm (Inject) CustomerService bằng @Autowired
    @Autowired
    private CustomerService customerService;

    // Xử lý request GET đến "/customers" để hiển thị danh sách
    @GetMapping("/customers")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("customers/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    // Xử lý request GET đến "/customers/info" để hiển thị chi tiết
    @GetMapping("/customers/info")
    public ModelAndView showInformation(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("customers/info");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
}
