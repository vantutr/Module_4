package com.codegym.baitap1.service;

import com.codegym.baitap1.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codegym.baitap1.model.Category;
import com.codegym.baitap1.repository.ICategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDTO> findAllDTO() {
        // Lấy tất cả entity
        Iterable<Category> categories = categoryRepository.findAll();
        // Dùng Stream API để chuyển đổi từng entity sang DTO
        return StreamSupport.stream(categories.spliterator(), false)
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    // Phương thức private để tái sử dụng logic chuyển đổi
    private CategoryDTO convertToCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}