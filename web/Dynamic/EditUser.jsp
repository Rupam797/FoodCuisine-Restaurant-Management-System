<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            padding: 20px;
        }
        form {
            max-width: 400px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Edit User</h1>
    <form action="/EditUser" method="post">
        <input type="hidden" name="email" value="<%= request.getParameter("email") %>">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= request.getParameter("name") %>" required>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="<%= request.getParameter("age") %>" required>

        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="Male" <%= "Male".equals(request.getParameter("gender")) ? "selected" : "" %>>Male</option>
            <option value="Female" <%= "Female".equals(request.getParameter("gender")) ? "selected" : "" %>>Female</option>
            <option value="Other" <%= "Other".equals(request.getParameter("gender")) ? "selected" : "" %>>Other</option>
        </select>

        <button type="submit">Update</button>
    </form>
</body>
</html>
