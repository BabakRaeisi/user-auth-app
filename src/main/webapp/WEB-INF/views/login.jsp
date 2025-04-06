<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { width: 300px; margin: 0 auto; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        
        <%-- AWS-Compatible Error Handling --%>
        <% if (request.getParameter("error") != null) { %>
            <p class="error">Invalid credentials. Try again.</p>
        <% } %>
        
        <% if (request.getParameter("registered") != null) { %>
            <p class="success">Registration successful! Please login.</p>
        <% } %>

        <form action="login" method="post">
            Email: <input type="email" name="email" required><br><br>
            Password: <input type="password" name="password" required><br><br>
            <input type="submit" value="Login">
        </form>
        <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    </div>
</body>
</html>