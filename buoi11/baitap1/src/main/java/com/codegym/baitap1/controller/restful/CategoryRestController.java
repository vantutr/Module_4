package com.codegym.baitap1.controller.restful;

import com.codegym.baitap1.dto.BlogDTO;
import com.codegym.baitap1.dto.CategoryDTO;
import com.codegym.baitap1.model.Blog;
import com.codegym.baitap1.model.Category;
import com.codegym.baitap1.service.IBlogService;
import com.codegym.baitap1.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOs = categoryService.findAllDTO();
        if (categoryDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}/blogs")
    public ResponseEntity<Page<BlogDTO>> getBlogsByCategory(
            @PathVariable Long id,
            @PageableDefault(value = 5) Pageable pageable) {

        Page<BlogDTO> blogDTOs = blogService.findAllByCategoryIdDTO(id, pageable);
        return new ResponseEntity<>(blogDTOs, HttpStatus.OK);
    }
}
