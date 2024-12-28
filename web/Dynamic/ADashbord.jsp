<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">

    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f2f5;
            color: #333;
            line-height: 1.6;
        }

        /* Header Section */
        header {
            background-color: #3e8e41;
            padding: 20px;
            text-align: center;
            color: white;
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 1200px;
            margin: 0 auto;
        }

        .logo img {
            width: 150px;
        }

        .title span {
            font-size: 28px;
            font-weight: bold;
            letter-spacing: 2px;
        }

        /* Logout Button Styles */
        #btn {
            background-color: #ff6347;
            color: white;
            border: none;
            padding: 12px 24px;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease, transform 0.3s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        #btn:hover {
            background-color: #d9534f;
            transform: translateY(-3px);
        }

        /* Main Content Section */
        .container {
            display: flex;
            justify-content: space-evenly;
            gap: 20px;
            padding: 50px 20px;
            flex-wrap: wrap;
            max-width: 1200px;
            margin: 0 auto;
        }

        /* Card Section */
        .card {
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 280px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            overflow: hidden;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-bottom: 30px;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        }

        .card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-bottom: 1px solid #ddd;
            transition: transform 0.3s ease;
        }

        .card img:hover {
            transform: scale(1.1);
        }

        .card h1 {
            font-size: 22px;
            color: #333;
            margin: 15px 0;
            font-weight: bold;
        }

        .card p {
            font-size: 15px;
            color: #777;
            margin-bottom: 20px;
            padding: 0 15px;
        }

        /* Footer Section */
        footer {
            background-color: #3e8e41;
            color: white;
            text-align: center;
            padding: 20px 0;
            position: relative;
            bottom: 0;
            width: 100%;
        }
                #btn a {
            text-decoration: none;
            color: white; /* Ensures the text color remains consistent */
        }

         #btn a:hover {
            text-decoration: none; /* Ensures no underline on hover */
        }


        /* Responsive Design */
        @media (max-width: 768px) {
            .container {
                flex-direction: column;
                align-items: center;
            }

            .card {
                width: 90%;
            }
        }
    </style>
</head>

<body>

    <!-- Header Section -->
    <header>
        <div class="nav">
            <a class="logo" href="../Dynamic/ADashbord.jsp">
                <img src="../Images/LOGO.jpg" alt="Admin Logo">
            </a>
            <div class="title">
                <span>Admin Dashboard</span>
            </div>
            <div class="logout">
                <form action="../Dynamic/AdminLogout" method="post">
                    <button type="submit" id="btn" name="logout-submit">
                        <a href="../Dynamic/login.jsp">Logout</a>
                    </button>
                </form>
            </div>
        </div>
    </header>

    <!-- Main Content Section -->
    <div class="container">
        <!-- User Details Card -->
        <div class="card">
            <a href="../Dynamic/UserDetails.jsp">
                <img src="../Images/user.jpg" alt="User Details">
                <h1>User Details</h1>
                <p>Manage user accounts, roles, and permissions.</p>
            </a>
        </div>

        <!-- Food Details Card -->
        <div class="card">
            <a href="../Dynamic/FoodDetails.jsp">
                <img src="../Images/image.png" alt="Food Details">
                <h1>Food Details</h1>
                <p>View and manage food items and categories.</p>
            </a>
        </div>

        <!-- Add Food Card -->
        <div class="card">
            <a href="../Dynamic/AddFood.jsp">
                <img src="../Images/add.png" alt="Add Food">
                <h1>Add Food</h1>
                <p>Add new food items to the system.</p>
            </a>
        </div>

        <!-- Booking Details Card -->
        <div class="card">
            <a href="../Dynamic/BookingDetails.jsp">
                <img src="../Images/table.png" alt="Booking Details">
                <h1>Booking Details</h1>
                <p>Manage and view all table bookings.</p>
            </a>
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 Admin Panel - All Rights Reserved</p>
    </footer>

</body>

</html>
