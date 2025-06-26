package com.codegym.thuchanh3.repository;

import com.codegym.thuchanh3.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
