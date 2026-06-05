<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Food Item</title>
    <link rel="stylesheet" href="../Styles/reset.css">
    <link rel="stylesheet" href="../Styles/globalStyles.css">
    <link rel="stylesheet" href="../Styles/components.css">
    <link rel="stylesheet" href="../Styles/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/Dynamic/ADashbord.jsp" class="back-link">
            <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
        </a>
        <h1>Add Food Item</h1>
        <div class="form-container">
            <form id="foodForm" action="${pageContext.request.contextPath}/admin/foods" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="food_id">Food ID:</label>
                    <input type="number" id="food_id" name="fid" required>
                </div>
                <div class="form-group">
                    <label for="food_name">Food Name:</label>
                    <input type="text" id="food_name" name="foodname" required>
                </div>
                <div class="form-group">
                    <label for="food_price">Price (₹):</label>
                    <input type="number" id="food_price" name="foodprice" step="0.01" required>
                </div>
                <div class="form-group">
                    <label for="food_img">Food Image (Upload Image):</label>
                    <input type="file" id="food_img" name="foodimg" accept="image/*" required>
                </div>
                <div class="form-group">
                    <label for="food_category">Food Category:</label>
                    <select id="food_category" name="foodcategory" required>
                        <option value="">Select a category</option>
                        <option value="Top Dishes">Top Dishes</option>
                        <option value="Breakfast">Breakfast</option>
                        <option value="Lunch">Lunch</option>
                        <option value="Dinner">Dinner</option>
                    </select>
                </div>
                <button type="submit" class="btn-submit">Add Food</button>
                <a href="${pageContext.request.contextPath}/Dynamic/ADashbord.jsp" class="btn-cancel">Cancel</a>
            </form>
        </div>
        <div id="message"></div>
    </div>
</body>
</html>
