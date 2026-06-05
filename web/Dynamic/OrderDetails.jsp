<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details | Admin</title>
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
                    <th colspan="7" style="text-align: center; color: #777;">End of Records</th>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>
