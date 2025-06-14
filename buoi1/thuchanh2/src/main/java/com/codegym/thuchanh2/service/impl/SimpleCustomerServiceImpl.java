package com.codegym.thuchanh2.service.impl;

import com.codegym.thuchanh2.model.Customer;
import com.codegym.thuchanh2.service.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleCustomerServiceImpl implements CustomerService {

    private static Map<Integer, Customer> customers;
    static{
        customers = new HashMap<>();
        customers.put(1, new Customer(1,"Tran Van A", "a@gmail.com", "Hà Nội"));
        customers.put(2, new Customer(2, "Tran Van B", "b@gmail.com", "Bắc Ninh"));
        customers.put(3, new Customer(3, "Tran Van C", "c@gmail.com", "Nghệ An"));
        customers.put(4, new Customer(4, "Tran Van D", "d@gmail.com", "Nghệ An"));
        customers.put(5, new Customer(5, "Tran Van E", "e@gmail.com", "Hải Phòng"));
    }
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }

}
