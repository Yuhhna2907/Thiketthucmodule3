package com.example.hethongquanlythuvien.DAO.InterfaceDAO;

import com.example.hethongquanlythuvien.model.Book;
import java.util.List;

public interface BookDAO {

    // Thêm sách mới
    boolean insert(Book book);

    // Cập nhật thông tin sách
    boolean update(Book book);

    // Xóa sách theo id
    boolean delete(Long bookId);

    // Lấy sách theo id
    Book getById(Long bookId);

    // Lấy tất cả sách
    List<Book> getAll();

    // Tìm sách theo mã sách
    Book getByBookCode(String bookCode);

    // Kiểm tra số lượng sách còn lại
    int getAvailableQuantity(Long bookId);
}

