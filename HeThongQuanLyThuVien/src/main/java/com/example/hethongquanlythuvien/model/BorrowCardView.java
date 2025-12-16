package com.example.hethongquanlythuvien.model;

import java.time.LocalDate;

public class BorrowCardView {
    private Long borrowId;
    private String bookName;
    private String author;
    private String studentName;
    private String className;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Boolean status;

    public BorrowCardView(Long borrowId, String bookName, String author,
                          String studentName, String className,
                          LocalDate borrowDate, LocalDate returnDate, Boolean status) {
        this.borrowId = borrowId;
        this.bookName = bookName;
        this.author = author;
        this.studentName = studentName;
        this.className = className;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
