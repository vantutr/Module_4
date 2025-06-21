package com.codegym.thuchanh1.service;

import com.codegym.thuchanh1.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void remove(int id);
}
