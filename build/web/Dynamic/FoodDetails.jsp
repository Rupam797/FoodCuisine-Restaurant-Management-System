<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Details | Admin</title>
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
        <h1>Food Details</h1>
        <table>
            <thead>
                <tr>
                    <th>FOOD ID</th>
                    <th>FOOD NAME</th>
                    <th>FOOD PRICE</th>
                    <th>FOOD CATEGORY</th>
                    <th>FOOD IMG</th>
                    <th>ACTION</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="food" items="${foods}">
            <tr>
                <td>${food.foodId}</td>
                <td>${food.foodName}</td>
                <td>₹${food.foodPrice}</td>
                <td>${food.foodCategory}</td>
                <td>${food.foodImg}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/foods" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="foodId" value="${food.foodId}">
                        <button type="submit" class="delete">Delete</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="6" style="text-align: center; color: #777;">End of Records</th>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>