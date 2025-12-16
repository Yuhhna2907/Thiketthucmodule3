package com.example.hethongquanlythuvien.ServiceLayer.Inteface;

import com.example.hethongquanlythuvien.model.Book;

import java.util.List;

public interface BookService {
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean removeBook(Long bookId);
    Book getBookById(Long bookId);
    List<Book> getAllBooks();
    int getAvailableQuantity(Long bookId);
}
