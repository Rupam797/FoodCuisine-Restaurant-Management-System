<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sessi = request.getSession(false);

    if (sessi == null || sessi.getAttribute("username") == null) {
        // Redirect to login if session is invalid
        response.sendRedirect("../Dynamic/login.jsp");
        return;
    }

    String username = (String) sessi.getAttribute("username");
    String userEmail = (String) sessi.getAttribute("userEmail");
    String userAge = (String) sessi.getAttribute("userAge");
    String userGender = (String) sessi.getAttribute("userGender");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <style>
        /* General Styles */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-attachment: fixed;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    color: #333;
}

.container {
    width: 100%;
    max-width: 450px;
    padding: 30px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    color: #26643b;
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
}

label {
    font-size: 1em;
    color: #333;
    margin-bottom: 5px;
}

input[type="text"],
input[type="file"],
select {
    width: 95%;
    padding: 10px;
    font-size: 1em;
    border: 2px solid #ccc;
    border-radius: 5px;
    transition: border 0.3s ease;
}

input[type="text"]:focus,
input[type="file"]:focus,
select:focus {
    border-color: #26643b;
    outline: none;
    box-shadow: 0 0 5px rgba(38, 100, 59, 0.5);
}

input[type="file"] {
    padding: 8px; /* Ensure consistent padding for file input */
}




.btn {
    display: block;
    width: 100%;
    padding: 12px;
    font-size: 1.1em;
    background-color: #26643b;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
    transition: background-color 0.3s ease;
    margin-bottom: 10px;
}

.btn_cancel{
    display: block;
    width: 95%;
    padding: 12px;
    font-size: 1.1em;
    background-color: #26643b;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
    transition: background-color 0.3s ease;
    margin-bottom: 10px;
     text-decoration: none; 
}

.btn_cancel:hover {
    background-color: #218838;
}


.btn:hover {
    background-color: #218838;
}

/* Responsive Styles */
@media (max-width: 768px) {
    .container {
        padding: 20px;
    }

    h1 {
        font-size: 24px;
    }

    .btn {
        font-size: 1em;
        padding: 10px;
    }
}

@media (max-width: 480px) {
    h1 {
        font-size: 20px;
    }

    input[type="text"],
    input[type="file"],
    select {
        font-size: 0.9em;
        padding: 8px;
    }

    .btn {
        font-size: 0.9em;
        padding: 8px 15px;
    }
}

        .profile-pic-container {
            position: relative;
            width: 120px;
            height: 120px;
            margin: 0 auto 20px auto;
            border-radius: 50%;
            overflow: hidden;
            border: 3px solid #26643b;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
            cursor: pointer;
            transition: transform 0.3s ease, border-color 0.3s ease;
        }
        .profile-pic-container:hover {
            transform: scale(1.05);
            border-color: #218838;
        }
        .profile-pic-container img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .profile-pic-container .overlay {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 30%;
            background: rgba(38, 100, 59, 0.8);
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            font-size: 0.8em;
            opacity: 0;
            transition: opacity 0.3s ease;
        }
        .profile-pic-container:hover .overlay {
            opacity: 1;
        }
    </style>
    <!-- FontAwesome icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body style="background-image: url(../Images/Back.jpg)">
    <div class="container">
        <h1>Edit Profile</h1>
        <form action="${pageContext.request.contextPath}/EditProfile" method="post" enctype="multipart/form-data">
            <div class="profile-pic-container" title="Click to upload profile picture">
                <img id="previewPic" src="${pageContext.request.contextPath}/ProfilePic?email=<%= userEmail %>" alt="Profile Picture">
                <div class="overlay">
                    <i class="fa-solid fa-camera"></i>&nbsp;Upload
                </div>
            </div>
            <div class="form-group">
                <label for="username">New Username:</label>
                <input type="text" id="username" name="username" value="<%= username %>" required>
            </div>
            <div class="form-group">
                <label for="age">New Age:</label>
                <input type="text" id="age" name="age" value="<%= userAge %>" required>
            </div>
            <div class="form-group">
                <label for="gender">New Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="Male" <%= "Male".equals(userGender) ? "selected" : "" %>>Male</option>
                    <option value="Female" <%= "Female".equals(userGender) ? "selected" : "" %>>Female</option>
                    <option value="Other" <%= "Other".equals(userGender) ? "selected" : "" %>>Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="profilePic">Upload Profile Picture:</label>
                <input type="file" id="profilePic" name="profilePic" accept="image/*">
            </div>
            <button type="submit" class="btn">Save Changes</button>
            <a href="../Dynamic/Home.jsp" class="btn_cancel">Cancel</a>
        </form>
    </div>
    <script>
        document.getElementById('profilePic').addEventListener('change', function(e) {
            const file = e.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(event) {
                    document.getElementById('previewPic').src = event.target.result;
                };
                reader.readAsDataURL(file);
            }
        });

        document.querySelector('.profile-pic-container').addEventListener('click', function() {
            document.getElementById('profilePic').click();
        });
    </script>
</body>
</html>
