code register.jsp

USER@DESKTOP-GEMKK9O MINGW64 ~/Desktop/DevOps/project/user-auth-app/src/main/webapp/WEB-INF/views
$ <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { width: 300px; margin: 0 auto; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Register</h2>

        <%-- AWS-Compatible Error Handling --%>
        <% if (request.getParameter("error") != null) { %>
            <p class="error">
                <%= request.getParameter("error").equals("1") ? "Email already exists!" : "Database error. Try later." %>
            </p>
        <% } %>

        <form action="register" method="post">
            Name: <input type="text" name="name" required><br><br>
</htmliv>>Already have an account? <a href="login.jsp">Login here</a></p><br>
