<%@ page import="java.sql.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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

        input[type="text"], input[type="date"], input[type="number"] {
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
    <%
        String bookingId = request.getParameter("bookingId");
        String bookingDate = "";
        String customerName = "";
        String tableNo = "";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOKING WHERE BOOKING_ID = ?");
            ps.setString(1, bookingId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                bookingDate = rs.getString("BOOKING_DATE");
                customerName = rs.getString("CUSTOMER_NAME");
                tableNo = rs.getString("TABLE_NO");
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
    <form action="/EditBooking" method="post">
        <input type="hidden" name="bookingId" value="<%= bookingId %>">

        <label for="bookingDate">Booking Date:</label>
        <input type="date" id="bookingDate" name="bookingDate" value="<%= bookingDate %>" required>

        <label for="customerName">Customer Name:</label>
        <input type="text" id="customerName" name="customerName" value="<%= customerName %>" required>

        <label for="tableNo">Table No:</label>
        <input type="number" id="tableNo" name="tableNo" value="<%= tableNo %>" required>

        <button type="submit">Update</button>
    </form>
</body>
</html>
