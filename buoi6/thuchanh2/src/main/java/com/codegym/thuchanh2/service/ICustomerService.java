package com.codegym.thuchanh2.service;

import com.codegym.thuchanh2.model.Customer;

public interface ICustomerService {
    boolean saveWithStoredProcedure(Customer customer);
}
