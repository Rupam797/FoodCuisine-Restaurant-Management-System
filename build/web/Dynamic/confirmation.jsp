
<%@page import="java.util.List"%>
<%@page import="adminpack.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
    <title>Confirmation | Food Cuisine</title>
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

        .confirmation-container button:hover{
            background-color:#218838;
        }
        .product-list {
            text-align: center;
            margin-top: 20px;
            border: 1px dashed black;
            border-radius: 40px;
            margin-left: auto;
            margin-right: auto;
            max-width: 400px;
            position: relative;
            
        }
    </style>
</head>

<body> 
    <div class="confirmation-container"> 
        <h1>Payment Successful!</h1> 
        <p id="customer-name">Customer Name: <%= request.getParameter("name") %></p> 
        <p id="phone-number">Phone Number: <%= request.getParameter("phone") %></p> 
        <p id="total-amount">Amount Paid: <%= request.getParameter("amount") %></p> 
        <div class="product-list" id="product-list"> 
            <h3>Order Details:</h3> 
            <%  
                List<Product> products = (List<Product>) session.getAttribute("cartProducts"); 
                if (products != null) { 
                    for (Product product : products) { 
                        out.print("<p>" + product.getName() + " (Quantity: " + product.getQuantity() + ")</p>"); 
                    } 
                } 
            %> 
        </div> 
        <button onclick="goBack()">Go back to menu</button> 
    </div> 
 
    <script> 
        function goBack() { 
            window.location.href = '../Dynamic/menu.jsp'; 
        } 
    </script> 
</body> 
</html>
