

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
    <div class="payment-container">
       <div> <a href="../DynamicS/payment.jsp" class="logo">
            <img src="../Images/pay.png" alt="Food cuisine">
          </a> 
        </div>
        <div class="total-amount" id="total-amount">Total: ₹0</div>
        <div class="payment-section">
            <h2>Billing Details</h2>
            <div class="form-group">
                <label for="customer-name">Customer Name</label>
                <input type="text" id="customer-name" placeholder="Enter your name">
                <div class="error-message" id="customer-name-error">Enter your name</div>
            </div>
            <div class="form-group">
                <label for="phone-number">Phone Number</label>
                <input type="text" id="phone-number" placeholder="Enter your phone number" maxlength="10">
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
            <h2>OR Pay Using UPI</h2>
            <img src="../Images/QR.jpeg" alt="QR Code" class="qr-code">
        </div>

        <div class="payment-section">
            <button onclick="confirmPayment()">Confirm Payment</button>
        </div>
    </div>

    <script>
        function confirmPayment() {
            let valid = true;
                const elements = [
                    { id: 'customer-name', regex: /\S/, errorMessageId: 'customer-name-error' },
                    { id: 'phone-number', regex: /^\d{10}$/, errorMessageId: 'phone-number-error' },
                    { id: 'card-holder-name', regex: /\S/, errorMessageId: 'card-holder-name-error' },
                    { id: 'card-number', regex: /^\d{16}$/, errorMessageId: 'card-number-error' },
                    { id: 'expiry', regex: /^(0[1-9]|1[0-2])\/\d{2}$/, errorMessageId: 'expiry-error' },
                    { id: 'cvv', regex: /^\d{3}$/, errorMessageId: 'cvv-error' },
                ];

                elements.forEach(({ id, regex, errorMessageId }) => {
                    const input = document.getElementById(id);
                    const errorMessage = document.getElementById(errorMessageId);
                    if (!regex.test(input.value.trim())) {
                        input.classList.add('invalid');
                        errorMessage.style.display = 'block';
                        valid = false;
                    } 
                    else {
                        input.classList.remove('invalid');
                        errorMessage.style.display = 'none';
                    }
            });

            if (!valid) return;

            const customerName = document.getElementById('customer-name').value.trim();
            const phoneNumber = document.getElementById('phone-number').value.trim();
            const totalAmount = document.getElementById('total-amount').textContent;

            alert('Payment successful!');
            document.addEventListener("DOMContentLoaded", function() {
    // Extract total amount from the URL query parameter
    const urlParams = new URLSearchParams(window.location.search);
    const totalAmount = urlParams.get('amount');
    
    // Check if amount is available and display it
    if (totalAmount) {
        // Display the total amount in the corresponding div
        document.getElementById('total-amount').textContent = `Total: ₹${totalAmount}`;
    } else {
        console.error("Amount not found in the URL");
    }
});

 
            
        }

        // Input restrictions
        document.querySelectorAll('#phone-number, #card-number, #cvv').forEach(input => {
                input.addEventListener('input', function() {
                    this.value = this.value.replace(/\D/g, '');
                });
            });


    </script>

</body>
</html>
