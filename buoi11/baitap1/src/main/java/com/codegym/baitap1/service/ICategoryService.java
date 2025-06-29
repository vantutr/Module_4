package com.codegym.baitap1.service;

import com.codegym.baitap1.dto.CategoryDTO;
import com.codegym.baitap1.model.Category;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category> {
    List<CategoryDTO> findAllDTO();


}
