<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Booking</title>
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

        input[type="text"], input[type="date"] {
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
    <h1>Edit Booking</h1>
    <form action="${pageContext.request.contextPath}/admin/bookings" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="bookingId" value="${booking.bookingId}">

        <label for="bookingDate">Booking Date:</label>
        <input type="date" id="bookingDate" name="bookingDate" value="${booking.bookingDate}" required>

        <label for="customerName">Customer Name:</label>
        <input type="text" id="customerName" name="customerName" value="${booking.firstName}" required>

        <label for="tableNo">Table No / Type:</label>
        <input type="text" id="tableNo" name="tableNo" value="${booking.tableType}" required>

        <button type="submit">Update</button>
    </form>
</body>
</html>
