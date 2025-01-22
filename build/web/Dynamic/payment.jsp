<%@ page import="cartpack.Cart" %>
<%@ page import="cartpack.CartItem" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
    <title>Payment | Food Cuisine</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin-top: 120px;
            margin-bottom: 150px;
            background-color: #f5fcf4;
            
        }

        .payment-container {
            width: 600px;
            border: 1px solid black;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-sizing: border-box;
            margin-top: 100px;
        }

        .payment-section {
            margin-top: 20px;
            margin-bottom: 20px;
           
        }

        .payment-section h2 {
            margin-top: 0;
        }

        .payment-section button{
            background-color:   #26643b; 
            color: white;
            border: none;
            padding: 10px 15px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
            margin-left: 38%;
            justify-content: center;
            align-items: center;
        }

        .payment-section button:hover{
            background-color:#218838;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 95%;
            outline: none;
            border: none;
            border-radius: 8px;
            padding: 15px;
            font-size: 15px;
            background: whitesmoke;
            color: black;
            border: 1.5px solid #26643b; 
        }

        .form-group input.invalid {
            border-color: red;
        }

        .error-message {
            color: red;
            font-size: 15px;
            display: none;
        }

        .qr-code {
            display: block;
            margin: 0 auto;
            width: 150px;
            height: 150px;
        }

        .total-amount {
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
            margin-top: 10px;
        }
        
    </style>
</head>
<body>
    <form action="/PaymentServlet" method="post">
    <div class="payment-container">
       <div> <a href="../Dynamic/payment.jsp" class="logo">
            <img src="../Images/pay.png" alt="Food cuisine">
          </a> 
        </div>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        out.println("<p>Your cart is empty.</p>");
    } else {
        int totalAmount = cart.getTotal();
%>
      <div class="total-amount" id="total-amount">Total Amount: ₹<%= totalAmount %></div>
<%
    }
%>



        <div class="payment-section">
            <h2>Billing Details</h2>
            
            <div class="form-group">
                <label for="customer-name">Customer Name</label>
                <input type="text" id="customer"name="customer-name" placeholder="Enter your name">
                <div class="error-message" id="customer-name-error">Enter your name</div>
            </div>
            <div class="form-group">
                <label for="phone-number">Phone Number</label>
                <input type="text" id="phone" name="phone-number" placeholder="Enter your phone number" maxlength="10">
                <div class="error-message" id="phone-number-error">Enter a valid phone number</div>
            </div>
        </div>

        <div class="payment-section">
            <h2>Credit/Debit Card Payment</h2>
            <div class="form-group">
                <label for="card-holder-name">Card Holder Name</label>
                <input type="text" id="card-holder-name" placeholder="Enter card holder's name">
                <div class="error-message" id="card-holder-name-error">Enter card holder name</div>
            </div>
            <div class="form-group">
                <label for="card-number">Card Number</label>
                <input type="text" id="card-number" placeholder="Enter your card number" maxlength="16">
                <div class="error-message" id="card-number-error">Enter a valid card number</div>
            </div>
            <div class="form-group">
                <label for="expiry">Expiry Date</label>
                <input type="text" id="expiry" placeholder="MM/YY">
                <div class="error-message" id="expiry-error">Enter a valid expiry date</div>
            </div>
            <div class="form-group">
                <label for="cvv">CVV</label>
                <input type="text" id="cvv" placeholder="CVV" maxlength="3">
                <div class="error-message" id="cvv-error">Enter a valid CVV</div>
            </div>
        </div>

        <div class="payment-section">
            <h2>QR Pay Using UPI</h2>
            <img src="../Images/QR.jpeg" alt="QR Code" class="qr-code">
        </div>

        <div class="payment-section">
            <button>Confirm Payment</button>
        </div>
        
    </div>

    </form>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector("form");
        
        form.addEventListener("submit", function(event) {
            let isValid = true;
            
            // Validate Customer Name
            const customerName = document.getElementById("customer");
            const customerNameError = document.getElementById("customer-name-error");
            if (customerName.value.trim() === "") {
                customerNameError.style.display = "block";
                isValid = false;
            } else {
                customerNameError.style.display = "none";
            }
            
            // Validate Phone Number
            const phoneNumber = document.getElementById("phone");
            const phoneNumberError = document.getElementById("phone-number-error");
            const phoneRegex = /^[0-9]{10}$/;
            if (!phoneRegex.test(phoneNumber.value)) {
                phoneNumberError.style.display = "block";
                isValid = false;
            } else {
                phoneNumberError.style.display = "none";
            }
            
            // Validate Card Holder Name
            const cardHolderName = document.getElementById("card-holder-name");
            const cardHolderNameError = document.getElementById("card-holder-name-error");
            if (cardHolderName.value.trim() === "") {
                cardHolderNameError.style.display = "block";
                isValid = false;
            } else {
                cardHolderNameError.style.display = "none";
            }
            
            // Validate Card Number (simple validation for 16 digits)
            const cardNumber = document.getElementById("card-number");
            const cardNumberError = document.getElementById("card-number-error");
            const cardNumberRegex = /^[0-9]{16}$/;
            if (!cardNumberRegex.test(cardNumber.value)) {
                cardNumberError.style.display = "block";
                isValid = false;
            } else {
                cardNumberError.style.display = "none";
            }
            
            // Validate Expiry Date (MM/YY format)
            const expiry = document.getElementById("expiry");
            const expiryError = document.getElementById("expiry-error");
            const expiryRegex = /^(0[1-9]|1[0-2])\/([0-9]{2})$/;
            if (!expiryRegex.test(expiry.value)) {
                expiryError.style.display = "block";
                isValid = false;
            } else {
                expiryError.style.display = "none";
            }

            // Validate CVV (3 digits)
            const cvv = document.getElementById("cvv");
            const cvvError = document.getElementById("cvv-error");
            const cvvRegex = /^[0-9]{3}$/;
            if (!cvvRegex.test(cvv.value)) {
                cvvError.style.display = "block";
                isValid = false;
            } else {
                cvvError.style.display = "none";
            }

            // If form is invalid, prevent submission
            if (!isValid) {
                event.preventDefault();
            }
        });
    });
</script>

</body>
</html>
