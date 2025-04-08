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
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (Exception e) {
            response.sendRedirect("login.jsp?error=2");
        }
    }
}