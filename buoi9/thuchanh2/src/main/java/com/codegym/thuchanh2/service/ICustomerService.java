package com.codegym.thuchanh2.service;

import com.codegym.thuchanh2.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll() throws Exception;

    Customer findOne(Long id) throws Exception;
}
