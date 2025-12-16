package com.example.hethongquanlythuvien.model;

public class Student {

    private Long studentId;
    private String studentCode;
    private String fullName;
    private String className;

    // Constructor rỗng
    public Student() {
    }

    // Constructor đầy đủ tham số
    public Student(Long studentId, String studentCode,
                   String fullName, String className) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.className = className;
    }

    // Getter & Setter
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
