<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
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
            width: 100%;
            max-width: 400px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        input {
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 10px;
            background-color: #26643b;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .message {
            text-align: center;
            margin-top: 10px;
        }

        .message.error {
            color: red;
        }

        .message.success {
            color: green;
        }
    </style>
</head>
<body style="background-image: url(../Images/Back.jpg) ">
    <div class="container">
        <h2>Forgot Password</h2>
        <form action="/ForgotPassword" method="post">
            <label for="email">Enter your registered email:</label>
            <input type="email" id="email" name="Useremail" required>
            <br>
            <button type="submit">Send OTP</button>
        </form>
    </div>
</body>
</html>
