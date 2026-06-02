<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>OTP Verification</title>
     <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
     <link rel="stylesheet" href="../Styles/toast.css">
     <script src="../Js/toast.js"></script>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
     <style>
        /* General Styles */
        body {
            font-family: 'Arial', sans-serif;
            background-attachment: fixed;
 background-size: cover;
background-position: center;
background-repeat: no-repeat;
            margin: 0;
            padding: 0;
            
        }

        .container {
            max-width: 450px;
            margin: auto;
            padding: 30px;
            margin-top: 200px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #26643b; 
            margin-bottom: 20px;
        }

        label {
            font-size: 1.1em;
            color: #333;
            margin-bottom: 10px;
            display: block;
        }

        input[type="text"] {
            width: 95%;
            padding: 10px;
            font-size: 1em;
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 5px;
            transition: border 0.3s ease;
            
        }

        input[type="text"]:focus {
            border-color: #26643b; 
            outline: none;
        }

        button {
            width: 100%;
            padding: 12px;
            font-size: 1.1em;
            background-color: #26643b; 
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #218838;
        }

        .alert {
            padding: 10px;
            margin: 20px 0;
            background-color: #f8d7da;
            color: #721c24;
            border-radius: 5px;
            border: 1px solid #f5c6cb;
        }

        /* Responsive Design */
        @media screen and (max-width: 600px) {
            .container {
                padding: 20px;
                margin: 20px;
            }
        }
    </style>
</head>
<body style="background-image: url(../Images/Back.jpg)">

    <div class="container">
        <h2>OTP Verification</h2>

        <!-- Toast Alert Block -->
        <%
            String toastMsg = (String) request.getAttribute("alertMessage");
            if (toastMsg == null) {
                toastMsg = (String) request.getAttribute("errorMessage");
            }
            String toastTyp = "error"; 
            
            HttpSession s = request.getSession(false);
            if (toastMsg == null && s != null) {
                toastMsg = (String) s.getAttribute("toastMessage");
                toastTyp = (String) s.getAttribute("toastType");
                if (toastMsg != null) {
                    s.removeAttribute("toastMessage");
                    s.removeAttribute("toastType");
                }
            }

            if (toastMsg != null) {
                String type = (toastTyp != null) ? toastTyp : "error";
        %>
            <script>
                document.addEventListener("DOMContentLoaded", function() {
                    showToast("<%= toastMsg %>", "<%= type %>");
                });
            </script>
        <%
            }
        %>

        <!-- OTP Verification Form -->
        <form action="/VerifyOtpVerification" method="post">
            <label for="otp"><b>Enter OTP:</b></label>
            <input type="text" name="otp" id="otp" required>

            <button type="submit">Verify OTP</button>
        </form>
    </div>

</body>
</html>
