package com.codegym.thuchanh2.repository;

import com.codegym.thuchanh2.model.Customer;

public interface ICustomerRepository {
    boolean saveWithStoredProcedure(Customer customer);
}
