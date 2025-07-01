package com.codegym.baitap1.controller.restful;

import com.codegym.baitap1.dto.BlogDTO;
import com.codegym.baitap1.dto.CategoryDTO;
import com.codegym.baitap1.model.Blog;
import com.codegym.baitap1.service.IBlogService;
import com.codegym.baitap1.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogRestController {
    @Autowired
    private IBlogService blogService;


    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<BlogDTO>> getAllBlogs(
            @PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC)
            Pageable pageable) {

        Page<BlogDTO> blogDTOs = blogService.findAllDTO(pageable);
        return new ResponseEntity<>(blogDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Blog blog = blogOptional.get();
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        blogDTO.setSummary(blog.getSummary());
        blogDTO.setAuthor(blog.getAuthor());
        blogDTO.setCreationDate(blog.getCreationDate());
        if(blog.getCategory() != null) {
            blogDTO.setCategory(new CategoryDTO(blog.getCategory().getId(), blog.getCategory().getName()));
        }
        return new ResponseEntity<>(blogDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BlogDTO>> searchBlogs(
            @RequestParam String title,
            @PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC)
            Pageable pageable) {

        Page<BlogDTO> result = blogService.findByTitleContainingDTO(title, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
