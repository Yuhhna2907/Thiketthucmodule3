package com.example.hethongquanlythuvien.ServiceLayer.Inteface;

import com.example.hethongquanlythuvien.model.Student;

import java.util.List;

public interface StudentService {
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean removeStudent(Long studentId);
    Student getStudentById(Long studentId);
    List<Student> getAllStudents();
}
