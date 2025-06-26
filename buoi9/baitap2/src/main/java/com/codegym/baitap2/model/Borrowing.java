package com.codegym.baitap2.model;

public class Borrowing {
    private Long id;
    private String code; // Mã mượn sách
    private Long bookId;

    public Borrowing() {
    }

    public Borrowing(Long id, String code, Long bookId) {
        this.id = id;
        this.code = code;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}