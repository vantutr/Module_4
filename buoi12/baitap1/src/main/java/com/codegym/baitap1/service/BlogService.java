package com.codegym.baitap1.service;

import com.codegym.baitap1.dto.BlogDTO;
import com.codegym.baitap1.dto.CategoryDTO;
import com.codegym.baitap1.model.Blog;
import com.codegym.baitap1.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findByTitleContaining(String title, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<BlogDTO> findByTitleContainingDTO(String title, Pageable pageable) {
        // Gọi phương thức repository đã có sẵn của bạn
        Page<Blog> blogPage = blogRepository.findByTitleContainingIgnoreCase(title, pageable);

        // Dùng hàm map có sẵn của Page để chuyển đổi nội dung sang DTO
        return blogPage.map(this::convertToBlogDTO);
    }

    @Override
    public Page<Blog> findAllByCategoryId(Long categoryId, Pageable pageable) {
        return blogRepository.findAllByCategory_Id(categoryId, pageable);
    }

    private BlogDTO convertToBlogDTO(Blog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setSummary(blog.getSummary());
        blogDTO.setContent(blog.getContent());
        blogDTO.setAuthor(blog.getAuthor());
        blogDTO.setCreationDate(blog.getCreationDate());
        if (blog.getCategory() != null) {
            blogDTO.setCategory(new CategoryDTO(blog.getCategory().getId(), blog.getCategory().getName()));
        }
        return blogDTO;
    }

    @Override
    public Page<BlogDTO> findAllDTO(Pageable pageable) {
        Page<Blog> blogPage = blogRepository.findAll(pageable);
        return blogPage.map(this::convertToBlogDTO);
    }

    @Override
    public Page<BlogDTO> findAllByCategoryIdDTO(Long categoryId, Pageable pageable) {
        Page<Blog> blogPage = blogRepository.findAllByCategory_Id(categoryId, pageable);
        return blogPage.map(this::convertToBlogDTO);
    }
}
