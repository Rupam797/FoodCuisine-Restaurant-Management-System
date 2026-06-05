<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="../Styles/reset.css">
    <link rel="stylesheet" href="../Styles/globalStyles.css">
    <link rel="stylesheet" href="../Styles/components.css">
    <link rel="stylesheet" href="../Styles/admin.css">
</head>

<body>

    <!-- Header Section -->
    <header>
        <div class="nav">
            <a class="logo" href="../Dynamic/ADashbord.jsp">
                <img src="../Images/logo.jpeg" alt="Admin Logo">
            </a>
            <div class="title">
                <span>Admin Dashboard</span>
            </div>
            <div class="logout">
                <form action="${pageContext.request.contextPath}/Logout" method="post">
                    <button type="submit" id="btn" name="logout-submit">Logout</button>
                </form>
            </div>
        </div>
    </header>

    <div class="container">
        <h1>Admin Dashboard</h1>
        <div class="cards-wrapper">
            <!-- User Details Card -->
            <div class="card">
                <a href="${pageContext.request.contextPath}/admin/users">
                    <img src="../Images/user.jpg" alt="User Details">
                    <h2>User Details</h2>
                    <p>Manage user accounts, roles, and permissions.</p>
                </a>
            </div>

            <!-- Food Details Card -->
            <div class="card">
                <a href="${pageContext.request.contextPath}/admin/foods">
                    <img src="../Images/image.png" alt="Food Details">
                    <h2>Food Details</h2>
                    <p>View and manage food items and categories.</p>
                </a>
            </div>

            <!-- Add Food Card -->
            <div class="card">
                <a href="${pageContext.request.contextPath}/Dynamic/AddFood.jsp">
                    <img src="../Images/add.png" alt="Add Food">
                    <h2>Add Food</h2>
                    <p>Add new food items to the system.</p>
                </a>
            </div>

            <!-- Booking Details Card -->
            <div class="card">
                <a href="${pageContext.request.contextPath}/admin/bookings">
                    <img src="../Images/table.png" alt="Booking Details">
                    <h2>Booking Details</h2>
                    <p>Manage and view all table bookings.</p>
                </a>
            </div>
            
            <!-- Order Details Card -->
            <div class="card">
                <a href="${pageContext.request.contextPath}/admin/orders">
                    <img src="../Images/Order.jpg" alt="Order Details">
                    <h2>Order Details</h2>
                    <p>Manage and view all food orders.</p>
                </a>
            </div>
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 Admin Panel - All Rights Reserved</p>
    </footer>

</body>

</html>
