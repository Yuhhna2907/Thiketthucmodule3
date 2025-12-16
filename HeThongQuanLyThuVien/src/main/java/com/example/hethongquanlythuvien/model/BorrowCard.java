package com.example.hethongquanlythuvien.model;

import java.time.LocalDate;

public class BorrowCard {

    private Long borrowId;
    private Long bookId;
    private Long studentId;
    private Boolean status;      // false: đang mượn, true: đã trả
    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Constructor rỗng
    public BorrowCard() {
    }

    // Constructor đầy đủ tham số
    public BorrowCard(Long borrowId, Long bookId, Long studentId,
                      Boolean status, LocalDate borrowDate, LocalDate returnDate) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getter & Setter
    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
}

