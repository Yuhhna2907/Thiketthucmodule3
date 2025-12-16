package com.example.hethongquanlythuvien.ServiceLayer.ImplService;

import com.example.hethongquanlythuvien.DAO.ImplDAO.BookDAOImpl;
import com.example.hethongquanlythuvien.DAO.InterfaceDAO.BookDAO;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.BookService;
import com.example.hethongquanlythuvien.model.Book;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO = new BookDAOImpl();

    @Override
    public boolean addBook(Book book) {
        if (book == null || book.getBookCode() == null || book.getBookCode().isEmpty()) {
            return false; // dữ liệu không hợp lệ
        }

        // Kiểm tra xem mã sách đã tồn tại chưa
        Book existing = bookDAO.getByBookCode(book.getBookCode());
        if (existing != null) {
            return false; // sách đã tồn tại
        }

        return bookDAO.insert(book);
    }

    @Override
    public boolean updateBook(Book book) {
        if (book == null || book.getBookId() == null) {
            return false; // dữ liệu không hợp lệ
        }
        return bookDAO.update(book);
    }

    @Override
    public boolean removeBook(Long bookId) {
        if (bookId == null) {
            return false; // id không hợp lệ
        }
        return bookDAO.delete(bookId);
    }

    @Override
    public Book getBookById(Long bookId) {
        if (bookId == null) {
            return null;
        }
        return bookDAO.getById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAll();
    }

    @Override
    public int getAvailableQuantity(Long bookId) {
        if (bookId == null) {
            return 0;
        }
        return bookDAO.getAvailableQuantity(bookId);
    }
}
