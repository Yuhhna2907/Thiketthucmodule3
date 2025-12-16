package com.example.hethongquanlythuvien.DAO.ImplDAO;

import com.example.hethongquanlythuvien.DAO.InterfaceDAO.StudentDAO;
import com.example.hethongquanlythuvien.model.Student;
import com.example.hethongquanlythuvien.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean insert(Student student) {
        String sql = "INSERT INTO student (student_code, full_name, class_name) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getStudentCode());
            ps.setString(2, student.getFullName());
            ps.setString(3, student.getClassName());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi insert student: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE students SET student_code = ?, full_name = ?, class_name = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getStudentCode());
            ps.setString(2, student.getFullName());
            ps.setString(3, student.getClassName());
            ps.setLong(4, student.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi update student: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Long studentId) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi delete student: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Student getById(Long studentId) {
        String sql = "SELECT student_id, student_code, full_name, class_name FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getLong("student_id"),
                            rs.getString("student_code"),
                            rs.getString("full_name"),
                            rs.getString("class_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi getById student: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT student_id, student_code, full_name, class_name FROM students";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getLong("student_id"),
                        rs.getString("student_code"),
                        rs.getString("full_name"),
                        rs.getString("class_name")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi getAll student: " + e.getMessage());
        }
        return students;
    }

    @Override
    public Student getByStudentCode(String studentCode) {
        String sql = "SELECT student_id, student_code, full_name, class_name FROM students WHERE student_code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getLong("student_id"),
                            rs.getString("student_code"),
                            rs.getString("full_name"),
                            rs.getString("class_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi getByStudentCode student: " + e.getMessage());
        }
        return null;
    }
}