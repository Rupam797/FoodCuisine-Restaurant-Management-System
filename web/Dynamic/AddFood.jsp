<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Food Item</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 100%;
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-size: 14px;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        select,
        input[type="file"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        #message {
            margin-top: 20px;
            text-align: center;
            color: green;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add Food Item</h1>
        <form id="foodForm" action="/AddFood" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="food_id">Food ID:</label>
                <input type="number" id="food_id" name="fid" required>
            </div>
            <div class="form-group">
                <label for="food_name">Food Name:</label>
                <input type="text" id="food_name" name="foodname" required>
            </div>
            <div class="form-group">
                <label for="food_price">Price:</label>
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
            <button type="submit">Add Food</button>
        </form>
        <div id="message"></div>
    </div>
</body>
</html>
