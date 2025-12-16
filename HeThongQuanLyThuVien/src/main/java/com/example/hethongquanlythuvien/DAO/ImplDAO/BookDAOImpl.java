package com.example.hethongquanlythuvien.DAO.ImplDAO;

import com.example.hethongquanlythuvien.DAO.InterfaceDAO.BookDAO;
import com.example.hethongquanlythuvien.DBConnection;
import com.example.hethongquanlythuvien.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public boolean insert(Book book) {
        String sql = "INSERT INTO books (book_code, book_name, author, description, quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getBookCode());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getDescription());
            ps.setInt(5, book.getQuantity());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        String sql = "UPDATE books SET book_code=?, book_name=?, author=?, description=?, quantity=? WHERE book_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getBookCode());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getDescription());
            ps.setInt(5, book.getQuantity());
            ps.setLong(6, book.getBookId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long bookId) {
        String sql = "DELETE FROM books WHERE book_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bookId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Book getById(Long bookId) {
        String sql = "SELECT * FROM books WHERE book_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBook(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getByBookCode(String bookCode) {
        String sql = "SELECT * FROM books WHERE book_code=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBook(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getAvailableQuantity(Long bookId) {
        String sql = "SELECT quantity FROM books WHERE book_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Helper method: map ResultSet sang Book
    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getLong("book_id"));
        book.setBookCode(rs.getString("book_code"));
        book.setBookName(rs.getString("book_name"));
        book.setAuthor(rs.getString("author"));
        book.setDescription(rs.getString("description"));
        book.setQuantity(rs.getInt("quantity"));
        return book;
    }
}
