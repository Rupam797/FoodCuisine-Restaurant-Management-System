<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Booking</title>
    <link rel="stylesheet" href="../Styles/reset.css">
    <link rel="stylesheet" href="../Styles/globalStyles.css">
    <link rel="stylesheet" href="../Styles/components.css">
    <link rel="stylesheet" href="../Styles/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/admin/bookings" class="back-link">
            <i class="fa-solid fa-arrow-left"></i> Back to Booking Details
        </a>
        <h1>Edit Booking</h1>
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/admin/bookings" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="bookingId" value="${booking.bookingId}">

                <div class="form-group">
                    <label for="bookingDate">Booking Date:</label>
                    <input type="date" id="bookingDate" name="bookingDate" value="${booking.bookingDate}" required>
                </div>

                <div class="form-group">
                    <label for="customerName">Customer Name:</label>
                    <input type="text" id="customerName" name="customerName" value="${booking.firstName}" required>
                </div>

                <div class="form-group">
                    <label for="tableNo">Table No / Type:</label>
                    <input type="text" id="tableNo" name="tableNo" value="${booking.tableType}" required>
                </div>

                <button type="submit" class="btn-submit">Update</button>
                <a href="${pageContext.request.contextPath}/admin/bookings" class="btn-cancel">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
