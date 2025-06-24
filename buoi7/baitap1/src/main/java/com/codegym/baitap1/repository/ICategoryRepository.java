package com.codegym.baitap1.repository;
import com.codegym.baitap1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}