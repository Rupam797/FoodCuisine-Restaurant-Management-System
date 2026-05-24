<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Details | Admin</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f7f6; margin: 0; padding: 20px; }
        h1 { text-align: center; color: #4CAF50; margin-bottom: 20px; }
        table { width: 100%; margin-top: 20px; border-collapse: collapse; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); border-radius: 10px; overflow: hidden; }
        th, td { padding: 12px 20px; text-align: left; font-size: 14px; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        tr:hover { background-color: #ddd; }
        button { padding: 8px 16px; margin: 5px; border: none; cursor: pointer; font-size: 14px; border-radius: 5px; transition: background-color 0.3s ease; }
        button.edit { background-color: #4CAF50; color: white; }
        button.edit:hover { background-color: #45a049; }
        button.delete { background-color: #f44336; color: white; }
        button.delete:hover { background-color: #da190b; }
        tfoot { background-color: #f9f9f9; text-align: center; font-size: 12px; }
        tfoot th { padding: 10px 0; color: #777; }
    </style>
</head>
<body>
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
                <th colspan="6">End of Records</th>
            </tr>
        </tfoot>
    </table>
</body>
</html>