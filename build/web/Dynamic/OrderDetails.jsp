<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details | Admin</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f7f6; margin: 0; padding: 20px; }
        h1 { text-align: center; color: #4CAF50; margin-bottom: 20px; }
        table { width: 100%; margin-top: 20px; border-collapse: collapse; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); border-radius: 10px; overflow: hidden; }
        th, td { padding: 12px 20px; text-align: left; font-size: 14px; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        tr:hover { background-color: #ddd; }
        tfoot { background-color: #f9f9f9; text-align: center; font-size: 12px; }
        tfoot th { padding: 10px 0; color: #777; }
    </style>
</head>
<body>
    <h1>Order Details</h1>
    <table>
        <thead>
            <tr>
                <th>ORDER ID</th>
                <th>CUSTOMER NAME</th>
                <th>PHONE</th>
                <th>FOOD DETAILS</th>
                <th>TOTAL AMOUNT</th>
                <th>PAYMENT STATUS</th>
                <th>ORDER DATE</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.customerName}</td>
            <td>${order.phoneNo}</td>
            <td>${order.foodDetails}</td>
            <td>₹${order.totalAmount}</td>
            <td>${order.paymentStatus}</td>
            <td>${order.orderDate}</td>
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
