<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet" href="../Styles/toast.css">
    <script src="../Js/toast.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500;600&family=Raleway:wght@400;500;600&display=swap');
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Raleway', sans-serif;
            background-color: #f5fcf4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }

        .container {
            background-color: #d6e5d8;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 400px;
        }

        h2 {
            font-family: 'Poppins', sans-serif;
            text-align: center;
            margin-bottom: 20px;
            color: #26643b;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        label {
            font-size: 15px;
            color: #3b413a;
            font-weight: 500;
        }

        input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 2px solid #b2c9b6;
            border-radius: 6px;
            font-family: 'Raleway', sans-serif;
            box-sizing: border-box;
            background-color: #fcfdfc;
        }

        input:focus {
            border-color: #26643b;
            outline: none;
            background-color: white;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #26643b;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-family: 'Poppins', sans-serif;
            font-size: 16px;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #1e502f;
        }

        .back-link {
            display: inline-block;
            margin-bottom: 15px;
            color: #26643b;
            text-decoration: none;
            font-size: 14px;
            font-weight: 600;
            font-family: 'Poppins', sans-serif;
        }

        .back-link:hover {
            color: #1e502f;
        }
    </style>
</head>
<% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            showToast("<%= errorMessage %>", "error");
        });
    </script>
<% 
    }
%>
<body style="background-image: url(../Images/Back.jpg)">
    <div class="container">
        <a href="${pageContext.request.contextPath}/Dynamic/login.jsp" class="back-link">
            <i class="fa-solid fa-arrow-left"></i> Back to Login
        </a>
        <h2>Reset Password</h2>
        <form action="${pageContext.request.contextPath}/ResetPassword" method="post">
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required>
            <button type="submit">Reset Password</button>
        </form>
    </div>
</body>
</html>
