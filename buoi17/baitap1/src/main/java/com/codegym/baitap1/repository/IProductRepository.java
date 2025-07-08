package com.codegym.baitap1.repository;

import com.codegym.baitap1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
