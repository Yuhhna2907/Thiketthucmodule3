package com.example.hethongquanlythuvien.DAO.ImplDAO;

import com.example.hethongquanlythuvien.DAO.InterfaceDAO.BorrowCardDAO;
import com.example.hethongquanlythuvien.DBConnection;
import com.example.hethongquanlythuvien.model.BorrowCard;
import com.example.hethongquanlythuvien.model.BorrowCardView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowCardDAOImpl implements BorrowCardDAO {

    @Override
    public boolean insert(BorrowCard borrowCard) {
        String sql = "INSERT INTO borrow_cards (book_id, student_id, status, borrow_date, return_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, borrowCard.getBookId());
            ps.setLong(2, borrowCard.getStudentId());
            ps.setBoolean(3, borrowCard.getStatus());
            ps.setDate(4, Date.valueOf(borrowCard.getBorrowDate()));
            ps.setDate(5, Date.valueOf(borrowCard.getReturnDate()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi insert BorrowCard: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(BorrowCard borrowCard) {
        String sql = "UPDATE borrow_cards SET book_id = ?, student_id = ?, status = ?, borrow_date = ?, return_date = ? WHERE borrow_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, borrowCard.getBookId());
            ps.setLong(2, borrowCard.getStudentId());
            ps.setBoolean(3, borrowCard.getStatus());
            ps.setDate(4, Date.valueOf(borrowCard.getBorrowDate()));
            ps.setDate(5, Date.valueOf(borrowCard.getReturnDate()));
            ps.setLong(6, borrowCard.getBorrowId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi update BorrowCard: " + e.getMessage());
        }
        return false;
    }

    @Override
    public BorrowCard getById(Long borrowId) {
        String sql = "SELECT * FROM borrow_cards WHERE borrow_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, borrowId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BorrowCard(
                            rs.getLong("borrow_id"),
                            rs.getLong("book_id"),
                            rs.getLong("student_id"),
                            rs.getBoolean("status"),
                            rs.getDate("borrow_date").toLocalDate(),
                            rs.getDate("return_date").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi getById BorrowCard: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<BorrowCard> getAll() {
        List<BorrowCard> list = new ArrayList<>();
        String sql = "SELECT * FROM borrow_cards";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BorrowCard card = new BorrowCard(
                        rs.getLong("borrow_id"),
                        rs.getLong("book_id"),
                        rs.getLong("student_id"),
                        rs.getBoolean("status"),
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate()
                );
                list.add(card);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi getAll BorrowCard: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<BorrowCardView> searchBorrowing(String bookName, String studentName) {
        List<BorrowCardView> list = new ArrayList<>();
        String sql = "SELECT bc.borrow_id, bc.borrow_date, bc.return_date, bc.status, " +
                "b.book_name, b.author, s.full_name, s.class_name " +
                "FROM borrow_cards bc " +
                "JOIN books b ON bc.book_id = b.book_id " +
                "JOIN students s ON bc.student_id = s.student_id " +
                "WHERE bc.status = false " +
                "AND (b.book_name LIKE ? OR ? IS NULL) " +
                "AND (s.full_name LIKE ? OR ? IS NULL)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookName != null ? "%" + bookName + "%" : null);
            ps.setString(2, bookName);
            ps.setString(3, studentName != null ? "%" + studentName + "%" : null);
            ps.setString(4, studentName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowCardView view = new BorrowCardView(
                            rs.getLong("borrow_id"),
                            rs.getString("book_name"),
                            rs.getString("author"),
                            rs.getString("full_name"),
                            rs.getString("class_name"),
                            rs.getDate("borrow_date").toLocalDate(),
                            rs.getDate("return_date").toLocalDate(),
                            rs.getBoolean("status")
                    );
                    list.add(view);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi searchBorrowing BorrowCardView: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

}