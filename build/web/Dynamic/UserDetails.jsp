<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details | Admin</title>
    <link rel="stylesheet" href="../Styles/reset.css">
    <link rel="stylesheet" href="../Styles/globalStyles.css">
    <link rel="stylesheet" href="../Styles/components.css">
    <link rel="stylesheet" href="../Styles/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/Dynamic/ADashbord.jsp" class="back-link">
            <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
        </a>
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
                    <th colspan="7" style="text-align: center; color: #777;">End of Records</th>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>
