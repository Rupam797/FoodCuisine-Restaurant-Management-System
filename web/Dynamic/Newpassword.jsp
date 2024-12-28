<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reset Password</title>
  <style>* {
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
<body>
  <div class="container">
    <h2>Reset Your Password</h2>
    <form id="resetPasswordForm">
      <label for="newPassword">New Password</label>
      <input type="password" id="newPassword" name="newPassword" placeholder="Enter new password" required>

      <label for="confirmPassword">Confirm Password</label>
      <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required>

      <button type="submit">Reset Password</button>
    </form>

    <p id="message" class="message"></p>
  </div>

    <script>
        document.getElementById('resetPasswordForm').addEventListener('submit', function(event) {
  event.preventDefault();

  const newPassword = document.getElementById('newPassword').value;
  const confirmPassword = document.getElementById('confirmPassword').value;

  const messageElement = document.getElementById('message');

  // Check if passwords match
  if (newPassword === confirmPassword) {
    messageElement.textContent = "Password reset successful!";
    messageElement.classList.remove("error");
    messageElement.classList.add("success");
  } else {
    messageElement.textContent = "Passwords do not match. Please try again.";
    messageElement.classList.remove("success");
    messageElement.classList.add("error");
  }
});

    </script>
</body>
</html>
