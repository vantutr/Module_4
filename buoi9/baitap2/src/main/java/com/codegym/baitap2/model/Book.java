package com.codegym.baitap2.model;

public class Book {
    private Long id;
    private String title;
    private String author;
    private int quantity;

    public Book() {
    }

    public Book(Long id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void borrow() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }

    public void giveBack() {
        this.quantity++;
    }
}
