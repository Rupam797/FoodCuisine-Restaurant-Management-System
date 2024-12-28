
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
  <title>Forgot Password - OTP</title>
  <link rel="stylesheet" href="styles.css">
</head>
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
  font-size: 30px;
}

form {
  margin-bottom: 20px;
}

input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
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

#otpSection {
  margin-top: 20px;
}

#message {
  margin-top: 10px;
  text-align: center;
  color: red;
}</style>
<body>
  <div class="container">
    <h2>Forgot Password</h2>
    <p></p>
    
    <form id="otpForm" action="/FetchEmailServlet" method="post">
      <label for="email">Enter Your Registered Email:</label>
      <input type="email" id="email" name="email" placeholder="Enter your email" required>
      <button type="submit" id="sendOtpButton">Send OTP</button>
    </form>

    <div id="otpSection" style="display: none;">
      <label for="otp">Enter the OTP sent to your email address:</label>
      <input type="text" id="otp" name="otp" placeholder="Enter OTP" required>
      <button type="button" id="verifyOtpButton">Verify OTP</button>
    </div>

    <p id="message"></p>
  </div>

  
</body>
</html>
