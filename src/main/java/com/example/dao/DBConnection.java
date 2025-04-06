package com.example.dao;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        // AWS RDS credentials will come from environment variables
        String dbUrl = System.getenv("JDBC_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }
}