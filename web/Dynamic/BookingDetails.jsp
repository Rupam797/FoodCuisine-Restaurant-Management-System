<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Details | Admin</title>
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
        <h1>Table Booking Details</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>FIRST NAME</th>
                    <th>LAST NAME</th>
                    <th>EMAIL</th>
                    <th>TABLE TYPE</th>
                    <th>PHONE</th>
                    <th>PLACEMENT</th>
                    <th>DATE</th>
                    <th>START TIME</th>
                    <th>END TIME</th>
                    <th>NOTE</th>
                    <th>ACTION</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="booking" items="${bookings}">
            <tr>
                <td>${booking.bookingId}</td>
                <td>${booking.firstName}</td>
                <td>${booking.lastName}</td>
                <td>${booking.email}</td>
                <td>${booking.tableType}</td>
                <td>${booking.phoneNo}</td>
                <td>${booking.placement}</td>
                <td>${booking.bookingDate}</td>
                <td>${booking.startTime}</td>
                <td>${booking.endTime}</td>
                <td>${booking.note}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/bookings" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="bookingId" value="${booking.bookingId}">
                        <button class="delete" type="submit">Delete</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/bookings" method="get" style="display:inline;">
                        <input type="hidden" name="bookingId" value="${booking.bookingId}">
                        <button class="edit" type="submit">Edit</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="12" style="text-align: center; color: #777;">End of Records</th>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>
