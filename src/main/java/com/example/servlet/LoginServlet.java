package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.validateUser(email, password);
            
            if (user != null) {
                // Successful login (store user in session for AWS stateless security)
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("welcome.jsp");
            } else {
                // Failed login
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (Exception e) {
            // Log errors for AWS CloudWatch
            System.err.println("Login Error: " + e.getMessage());
            response.sendRedirect("login.jsp?error=2");
        }
    }
}