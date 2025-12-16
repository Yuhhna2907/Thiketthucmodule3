package com.example.hethongquanlythuvien.DAO.InterfaceDAO;

import com.example.hethongquanlythuvien.model.Student;
import java.util.List;

public interface StudentDAO {

    // Thêm sinh viên mới
    boolean insert(Student student);

    // Cập nhật thông tin sinh viên
    boolean update(Student student);

    // Xóa sinh viên theo id
    boolean delete(Long studentId);

    // Lấy thông tin sinh viên theo id
    Student getById(Long studentId);

    // Lấy tất cả sinh viên
    List<Student> getAll();

    // Tìm sinh viên theo mã sinh viên
    Student getByStudentCode(String studentCode);
}

