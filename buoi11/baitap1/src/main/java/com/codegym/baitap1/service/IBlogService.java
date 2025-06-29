package com.codegym.baitap1.service;

import com.codegym.baitap1.dto.BlogDTO;
import com.codegym.baitap1.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGenerateService<Blog> {
    Page<BlogDTO> findAllDTO(Pageable pageable);
    Page<BlogDTO> findAllByCategoryIdDTO(Long categoryId, Pageable pageable);

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findByTitleContaining(String title, Pageable pageable);

    Page<Blog> findAllByCategoryId(Long categoryId, Pageable pageable);

}
