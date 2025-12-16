package com.example.hethongquanlythuvien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hethongquanlythuvien?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "tittom343";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}
