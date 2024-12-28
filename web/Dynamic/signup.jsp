<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
  <title>Sign Up | Food Cuisine</title>
  <link rel='stylesheet' href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'>
  <link rel="stylesheet" href="../Styles/style.css">
  

</head>
<body style="background-image: url(../Images/Back.jpg)">
  
<div class="container">
  <header>Sign Up</header>
  
  <form name="signupform" action="/SignupUser" method="post" id="signupForm">
    <div class="field name-field">
      <div class="input-field">
        <input type="text" placeholder="Enter your Name" class="name" name="Uname" required />
      </div>
      <span class="error name-error">
        <i class="bx bx-error-circle error-icon"></i>
        <p class="error-text">Please enter a valid Name</p>
      </span>
    </div>

    <div class="field email-field">
      <div class="input-field">
        <input type="email" placeholder="Enter your email" class="email" name="Uemail" required />
      </div>
      <span class="error email-error">
        <i class="bx bx-error-circle error-icon"></i>
        <p class="error-text">Please enter a valid email</p>
      </span>
    </div>

    <div class="field">
      <div class="input-field">
        <input type="number" placeholder="Enter your age" class="age" min="1" name="Uage" required />
      </div>
    </div>

    <div class="field">
      <label>Gender:</label>
      <input type="radio" id="male" name="Ugender" value="male" required>
      <label for="male">Male</label>
      <input type="radio" id="female" name="Ugender" value="female">
      <label for="female">Female</label>
      <input type="radio" id="other" name="Ugender" value="other">
      <label for="other">Others</label>
    </div>

    <div class="field create-password">
      <div class="input-field">
        <input type="password" placeholder="Password" class="password" name="Upassword" required />
        <i class="bx bx-hide show-hide"></i>
      </div>
      <span class="error password-error">
        <i class="bx bx-error-circle error-icon"></i>
        <p class="error-text">
          Please enter at least 8 characters with a number, symbol, small, and capital letter.
        </p>
      </span>
    </div>

    <div class="field confirm-password">
      <div class="input-field">
        <input type="password" placeholder="Confirm Password" class="cPassword" name="Ucpassword" required />
        <i class="bx bx-hide show-hide"></i>
      </div>
      <span class="error cPassword-error">
        <i class="bx bx-error-circle error-icon"></i>
        <p class="error-text">Passwords do not match</p>
      </span>
    </div>

    <div class="links">
      <a href="Email_forgot_password.jsp">Forgot Password</a>
      <a href="../Dynamic/login.jsp">Login</a>
    </div>
    <div class="input-field button">
      <input type="submit" value="Signup Now" onclick="callAlert"/>
    </div>
  </form>
</div>

<script>
const form = document.getElementById("signupForm"),
  nameField = form.querySelector(".name-field"),
  nameInput = nameField.querySelector(".name"),
  emailField = form.querySelector(".email-field"),
  emailInput = emailField.querySelector(".email"),
  passField = form.querySelector(".create-password"),
  passInput = passField.querySelector(".password"),
  cPassField = form.querySelector(".confirm-password"),
  cPassInput = cPassField.querySelector(".cPassword");

// Name Validation
function checkName() {
  const namePattern = /^[a-zA-Z\s]+$/;
  if (!nameInput.value.match(namePattern)) {
    return nameField.classList.add("invalid");
  }
  nameField.classList.remove("invalid");
}

// Email Validation
function checkEmail() {
  const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
  if (!emailInput.value.match(emailPattern)) {
    return emailField.classList.add("invalid");
  }
  emailField.classList.remove("invalid");
}

// Password Validation
function createPass() {
  const passPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
  if (!passInput.value.match(passPattern)) {
    return passField.classList.add("invalid");
  }
  passField.classList.remove("invalid");
}

// Confirm Password Validation
function confirmPass() {
  if (passInput.value !== cPassInput.value || cPassInput.value === "") {
    return cPassField.classList.add("invalid");
  }
  cPassField.classList.remove("invalid");
}

// Toggle Password Visibility
document.querySelectorAll(".show-hide").forEach((eyeIcon) => {
  eyeIcon.addEventListener("click", () => {
    const input = eyeIcon.parentElement.querySelector("input");
    if (input.type === "password") {
      input.type = "text";
      eyeIcon.classList.replace("bx-hide", "bx-show");
    } else {
      input.type = "password";
      eyeIcon.classList.replace("bx-show", "bx-hide");
    }
  });
});

// Form Submission
form.addEventListener("submit", (e) => {
  checkName();
  checkEmail();
  createPass();
  confirmPass();

  if (
    nameField.classList.contains("invalid") ||
    emailField.classList.contains("invalid") ||
    passField.classList.contains("invalid") ||
    cPassField.classList.contains("invalid")
  ) {
    e.preventDefault(); // Prevent form submission if validation fails
  }
});

// Keyup Event Listeners
nameInput.addEventListener("keyup", checkName);
emailInput.addEventListener("keyup", checkEmail);
passInput.addEventListener("keyup", createPass);
cPassInput.addEventListener("keyup", confirmPass);

</script>

</body>
</html>
