package com.codegym.customermanageaspect.service;

import com.codegym.customermanageaspect.model.Customer;
import com.codegym.customermanageaspect.service.exception.DuplicateEmailException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    private static final Map<Long, Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1L, new Customer(1L, "Nguyen Khac Nhat", "nhat@codegym.vn", "Hanoi"));
        customers.put(2L, new Customer(2L, "Dang Huy Hoa", "hoa.dang@codegym.vn", "Danang"));
        customers.put(3L, new Customer(3L, "Le Thi Chau", "chau.le@codegym.vn", "Saigon"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer findById(Long id) {
        return customers.get(id);
    }

    @Override
    public Customer save(Customer customer) throws DuplicateEmailException {
        // Nếu là tạo mới (chưa có id)
        if (customer.getId() == null) {
            // Kiểm tra email có trùng không
            for (Customer c : findAll()) {
                if (c.getEmail().equals(customer.getEmail())) {
                    throw new DuplicateEmailException("Email này đã tồn tại!");
                }
            }
            Long id = (long) (customers.size() + 1);
            customer.setId(id);
        }
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public void remove(Long id) {
        customers.remove(id);
    }
}
