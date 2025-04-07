package com.example.dao;

import com.example.model.User;
import java.sql.*;

public class UserDAO {
    
    // Register a new user (AWS RDS compatible)
    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            
            return pstmt.executeUpdate() > 0; // Returns true if insert succeeded
        }
    }

    // Validate user credentials (secure for AWS)
    public User validateUser(String email, String password) throws SQLException {
        String sql = "SELECT id, name, email FROM users WHERE email = ? AND password = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                return user; // Never return the password!
            }
        }
        return null; // Invalid credentials
    }
}