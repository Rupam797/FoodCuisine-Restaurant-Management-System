<%@ page import="java.util.*" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f5fcf4;
            padding: 20px;
            box-sizing: border-box;
        }
        .confirmation-container {
            width: 100%;
            max-width: 600px;
            border: 1px solid black;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            text-align: center;
            position: relative;
        }
        .confirmation-container h1 {
            margin-top: 0;
        }
        .confirmation-container p {
            margin: 10px 0;
            font-size: 17px;
        }
        .confirmation-container button {
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #26643b; 
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
        }

        .confirmation-container button:hover {
            background-color: #218838;
        }
        .product-list {
            text-align: center;
            margin-top: 20px;
            border: 1px dashed black;
            border-radius: 40px;
            margin-left: auto;
            margin-right: auto;
            max-width: 500px;
            position: relative;
        }
    </style>
</head>
<body>

<div class="confirmation-container">
    <div> <a href="/Dynamic/confirmation.jsp" class="logo">
            <img src="../Images/pay2.jpg" alt="Food cuisine">
          </a> 
        </div>
        <h1>Payment Successful!</h1>

    <div class="order-details">
        
        <p><strong>Customer Name:</strong> <%= session.getAttribute("customerName") != null ? session.getAttribute("customerName") : "N/A" %></p>
        <p><strong>Phone Number:</strong> <%= session.getAttribute("phoneNumber") != null ? session.getAttribute("phoneNumber") : "N/A" %></p>
        <p><strong>Total Amount:</strong> Rs.<%= session.getAttribute("totalAmount") != null ? session.getAttribute("totalAmount") : 0 %></p>
        <p><strong>Payment Status:</strong> <%= session.getAttribute("paymentStatus") != null ? session.getAttribute("paymentStatus") : "N/A" %></p>
        
        <div class="product-list">
    <p><strong>Order Details:</strong></p>
    <p>
        <%= session.getAttribute("foodDetails") != null ? session.getAttribute("foodDetails").toString().replaceAll(";", ";<br>") : "No items in order." %>
    </p>
</div>
    </div>

    
    <a href="/Dynamic/menu.jsp"><button>Go back to Menu</button></a>
</div>

</body>
</html>
