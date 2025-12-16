package com.example.hethongquanlythuvien.model;

public class Book {

    private Long bookId;
    private String bookCode;
    private String bookName;
    private String author;
    private String description;
    private Integer quantity;

    // Constructor rỗng
    public Book() {
    }

    // Constructor đầy đủ tham số
    public Book(Long bookId, String bookCode, String bookName,
                String author, String description, Integer quantity) {
        this.bookId = bookId;
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
    }

    // Getter & Setter
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

