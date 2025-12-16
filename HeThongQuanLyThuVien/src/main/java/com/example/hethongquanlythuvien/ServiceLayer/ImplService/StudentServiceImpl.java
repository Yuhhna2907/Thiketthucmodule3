package com.example.hethongquanlythuvien.ServiceLayer.ImplService;



import com.example.hethongquanlythuvien.DAO.ImplDAO.StudentDAOImpl;
import com.example.hethongquanlythuvien.DAO.InterfaceDAO.StudentDAO;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.StudentService;
import com.example.hethongquanlythuvien.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public boolean addStudent(Student student) {
        if (student == null || student.getStudentCode() == null || student.getStudentCode().isEmpty()) {
            return false; // không hợp lệ
        }

        // Kiểm tra xem mã sinh viên đã tồn tại chưa
        Student existing = studentDAO.getByStudentCode(student.getStudentCode());
        if (existing != null) {
            return false; // sinh viên đã tồn tại
        }

        return studentDAO.insert(student);
    }

    @Override
    public boolean updateStudent(Student student) {
        if (student == null || student.getStudentId() == null) {
            return false; // không hợp lệ
        }
        return studentDAO.update(student);
    }

    @Override
    public boolean removeStudent(Long studentId) {
        if (studentId == null) {
            return false; // không hợp lệ
        }
        return studentDAO.delete(studentId);
    }

    @Override
    public Student getStudentById(Long studentId) {
        if (studentId == null) {
            return null;
        }
        return studentDAO.getById(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            students = studentDAO.getAll();
        } catch (Exception e) {
            // Ghi log lỗi, có thể dùng Logger framework thay cho System.err
            System.err.println("Lỗi khi lấy danh sách học sinh: " + e.getMessage());
        }
        return students;
    }
}
