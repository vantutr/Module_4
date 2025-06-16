package com.codegym.thuchanh5.service;

import com.codegym.thuchanh5.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
}
