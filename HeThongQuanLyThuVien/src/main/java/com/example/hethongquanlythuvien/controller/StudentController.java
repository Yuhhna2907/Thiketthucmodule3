package com.example.hethongquanlythuvien.controller;


import com.example.hethongquanlythuvien.ServiceLayer.ImplService.StudentServiceImpl;
import com.example.hethongquanlythuvien.ServiceLayer.Inteface.StudentService;
import com.example.hethongquanlythuvien.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentController extends HttpServlet {

    private final StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.getRequestDispatcher("/student-add.jsp").forward(request, response);
                break;
            case "edit":
                Long studentId = Long.parseLong(request.getParameter("id"));
                Student student = studentService.getStudentById(studentId);
                request.setAttribute("student", student);
                request.getRequestDispatcher("/student-edit.jsp").forward(request, response);
                break;
            case "delete":
                Long idToDelete = Long.parseLong(request.getParameter("id"));
                studentService.removeStudent(idToDelete);
                response.sendRedirect(request.getContextPath() + "/students");
                break;
            default:
                List<Student> students = studentService.getAllStudents();
                request.setAttribute("students", students);
                request.getRequestDispatcher("/student-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String studentCode = request.getParameter("studentCode");
            String fullName = request.getParameter("fullName");
            String className = request.getParameter("className");

            Student student = new Student();
            student.setStudentCode(studentCode);
            student.setFullName(fullName);
            student.setClassName(className);

            studentService.addStudent(student);
            response.sendRedirect(request.getContextPath() + "/students");
        } else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String studentCode = request.getParameter("studentCode");
            String fullName = request.getParameter("fullName");
            String className = request.getParameter("className");

            Student student = new Student(id, studentCode, fullName, className);
            studentService.updateStudent(student);
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }
}

