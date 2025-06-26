package com.codegym.baitap2.service;

import com.codegym.baitap2.exception.BookOutOfStockException;
import com.codegym.baitap2.exception.InvalidCodeException;
import com.codegym.baitap2.model.Book;

import java.util.List;

public interface IBookService {

    List<Book> findAll();
    Book findById(Long id);
    String borrowBook(Long id) throws BookOutOfStockException;
    void returnBook(String code) throws InvalidCodeException;
}
