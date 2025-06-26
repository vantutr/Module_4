package com.codegym.customermanageaspect.service;

import com.codegym.customermanageaspect.model.Customer;
import com.codegym.customermanageaspect.service.exception.DuplicateEmailException;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer) throws DuplicateEmailException;

    void remove(Long id);
}