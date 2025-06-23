package com.example.blog_jpa.service;

import com.example.blog_jpa.model.Blog;

import java.util.List;

public interface IBlogService {
    public List<Blog> findAll();

    public Blog findById(Long id);

    public void save(Blog blog);

    public void update(Long id,Blog blog);

    public void delete(Long id);
}
