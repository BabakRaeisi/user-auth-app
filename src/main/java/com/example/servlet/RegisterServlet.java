package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.registerUser(user);
            
            if (success) {
                response.sendRedirect("login.jsp?registered=true");
            } else {
                // Forward to register.jsp (not accessible directly in WEB-INF)
                request.setAttribute("error", "Email already exists");
                request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println("AWS CloudWatch Log - Registration Error: " + e.getMessage());
            request.setAttribute("error", "Database error");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }
}
