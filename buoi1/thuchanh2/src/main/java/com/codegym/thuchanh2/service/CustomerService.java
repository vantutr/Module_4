package com.codegym.thuchanh2.service;

import com.codegym.thuchanh2.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);
}
