package com.codegym.baitap1.service;

import com.codegym.baitap1.model.Blog;

import java.util.List;
import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    void save(T blog);

    Optional<T> findById(Long id);

    void remove(Long id);
}
