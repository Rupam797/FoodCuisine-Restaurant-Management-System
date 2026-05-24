<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details | Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #4CAF50;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 20px;
            text-align: left;
            font-size: 14px;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        button {
            padding: 8px 16px;
            margin: 5px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        button.edit {
            background-color: #4CAF50;
            color: white;
        }

        button.edit:hover {
            background-color: #45a049;
        }

        button.delete {
            background-color: #f44336;
            color: white;
        }

        button.delete:hover {
            background-color: #da190b;
        }

        tfoot {
            background-color: #f9f9f9;
            text-align: center;
            font-size: 12px;
        }

        tfoot th {
            padding: 10px 0;
            color: #777;
        }
    </style>
</head>
<body>
    <h1>User Details</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>EMAIL</th>
                <th>AGE</th>
                <th>GENDER</th>
                <th>ROLE</th>
                <th>ACTION</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
            <td>${user.gender}</td>
            <td>${user.role}</td>
            <td>
                <form action="${pageContext.request.contextPath}/Dynamic/EditUser.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="email" value="${user.email}">
                    <input type="hidden" name="name" value="${user.name}">
                    <input type="hidden" name="age" value="${user.age}">
                    <input type="hidden" name="gender" value="${user.gender}">
                    <button type="submit" class="edit">Edit</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/users" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="email" value="${user.email}">
                    <button type="submit" class="delete">Delete</button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <th colspan="7">End of Records</th>
            </tr>
        </tfoot>
    </table>
</body>
</html>
