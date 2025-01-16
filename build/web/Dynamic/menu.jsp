<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="oracle.jdbc.*" %>
<%@page import="cartpack.CartItem"%>
<%@page import="cartpack.Cart"%>
<%@page import="java.util.*, javax.servlet.http.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
  <title>Menu | Food Cuisine</title>
  <link rel="stylesheet" href="../Styles/reset.css">
  <link rel="stylesheet" href="../Styles/globalStyles.css">
  <link rel="stylesheet" href="../Styles/components.css">
  <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
  <link rel="stylesheet" href="../Styles/menu.css">
  <link rel="stylesheet" href="../Styles/cart.css">
  <link rel="stylesheet" href="../Styles/cartcss.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body style="background-color:#f5fcf4;">
    <!-- Nav Section -->
    <div class="nav">
        <div class="container">
            <div class="nav__wrapper">
                <a href="../Dynamic/Home.jsp" class="logo">
                    <img src="../Images/logo.jpeg" alt="Food cuisine">
                </a>
                <nav>
                    <div class="nav__icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-menu">
                            <line x1="3" y1="12" x2="21" y2="12" />
                            <line x1="3" y1="6" x2="21" y2="6" />
                            <line x1="3" y1="18" x2="21" y2="18" />
                        </svg>
                    </div>
                    <div class="nav__bgOverlay"></div>
                    <ol class="nav__list">
                        <div class="nav__close">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x">
                                <line x1="18" y1="6" x2="6" y2="18" />
                                <line x1="6" y1="6" x2="18" y2="18" />
                            </svg>
                        </div>
                        <div class="nav_list_wrapper">
                            <li><a class="nav__link" href="../Dynamic/Home.jsp">Home</a></li>
                            <li><a class="nav__link" href="../Dynamic/menu.jsp">Menu</a></li>
                            <li><a class="nav__link" href="../Dynamic/about.jsp">About</a></li>
                            <li><a class="nav__link" href="../Dynamic/contact.jsp">Contact</a></li>
                            <li><a class="nav__link" href="../Dynamic/booking.jsp">Book Table</a></li>
                            <li>
                                <div class="icon-cart" onclick="openCart()">
                                    <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 4h1.5L9 16m0 0h8m-8 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4Zm8 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4Zm-8.5-3h9.25L19 7H7.312"/>
                                    </svg>
                                    <span>Cart</span>
                                </div>
                            </li>
                        </div>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
<!-- End Nav Section -->

 <!-- Start top Dishes -->
 <section id="dishGrid" data-aos="fade-down">
  <div class="container">
       <h1 class="page__title__text">
        Explore Our Menu
      </h1>
    <h2 class="dishGrid__title">Top Dishes</h2>
    <div class="dishGrid__wrapper">
      <%
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");
            String query = "SELECT FOOD_ID, FOOD_NAME, FOOD_PRICE, FOOD_IMG FROM FOODS WHERE FOOD_CATEGORY = 'Top Dishes'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int foodId = rs.getInt("FOOD_ID");
                String foodName = rs.getString("FOOD_NAME");
                int foodPrice = rs.getInt("FOOD_PRICE");
                String foodImg = rs.getString("FOOD_IMG");
      %>
      <div class="dishGrid__item" data-product-id="<%= foodId %>" data-product-name="<%= foodName %>" data-product-price="<%= foodPrice %>">
        <div class="dishGrid_item_img">
          <img src="../<%= foodImg %>" alt="<%= foodName %>">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title"><%= foodName %></h3>
          <div class="dishGrid_item_stars">
              <img src="../Images/3star.png" alt="3 star">
          </div>
          <h3 class="dishGrid_item_price">₹<%= foodPrice %></h3>
          <form action="/AddToCartServlet" method="post">
              <input type="hidden" name="foodId" value="<%= foodId %>">
          <button class="top_menu_btn" type="submit">Add to Cart</button>
           </form>
        </div>
      </div>
      <% 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
      %>
    </div>
  </div>
</section>
<!-- End top Dishes -->
 <!--  Breakfast Dishes -->
 <section id="dishGrid" data-aos="fade-down">
  <div class="container">
    <h2 class="dishGrid__title">Breakfast</h2>
    <div class="dishGrid__wrapper">
      <%
    
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");
            String query = "SELECT FOOD_ID, FOOD_NAME, FOOD_PRICE, FOOD_IMG FROM FOODS WHERE FOOD_CATEGORY = 'Breakfast'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int foodId = rs.getInt("FOOD_ID");
                String foodName = rs.getString("FOOD_NAME");
                int foodPrice = rs.getInt("FOOD_PRICE");
                String foodImg = rs.getString("FOOD_IMG");
      %>
      <div class="dishGrid__item" data-product-id="<%= foodId %>" data-product-name="<%= foodName %>" data-product-price="<%= foodPrice %>">
        <div class="dishGrid_item_img">
          <img src="../<%= foodImg %>" alt="<%= foodName %>">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title"><%= foodName %></h3>
          <div class="dishGrid_item_stars">
              <img src="../Images/3star.png" alt="3 star">
          </div>
          <h3 class="dishGrid_item_price">₹<%= foodPrice %></h3>
          <form action="/AddToCartServlet" method="post">
              <input type="hidden" name="foodId" value="<%= foodId %>">
          <button class="top_menu_btn" type="submit">Add to Cart</button>
           </form>
        </div>
      </div>
      <% 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
      %>
    </div>
  </div>
</section>

<!-- End Breakfast Dishes -->
<!-- Lunch Dishes -->
 <section id="dishGrid" data-aos="fade-down">
  <div class="container">
    <h2 class="dishGrid__title">Lunch</h2>
    <div class="dishGrid__wrapper">
      <%
     
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");
            String query = "SELECT FOOD_ID, FOOD_NAME, FOOD_PRICE, FOOD_IMG FROM FOODS WHERE FOOD_CATEGORY = 'Lunch'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int foodId = rs.getInt("FOOD_ID");
                String foodName = rs.getString("FOOD_NAME");
                int foodPrice = rs.getInt("FOOD_PRICE");
                String foodImg = rs.getString("FOOD_IMG");
      %>
      <div class="dishGrid__item" data-product-id="<%= foodId %>" data-product-name="<%= foodName %>" data-product-price="<%= foodPrice %>">
        <div class="dishGrid_item_img">
          <img src="../<%= foodImg %>" alt="<%= foodName %>">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title"><%= foodName %></h3>
          <div class="dishGrid_item_stars">
              <img src="../Images/3star.png" alt="3 star">
          </div>
          <h3 class="dishGrid_item_price">₹<%= foodPrice %></h3>
          <form action="/AddToCartServlet" method="post">
              <input type="hidden" name="foodId" value="<%= foodId %>">
          <button class="top_menu_btn" type="submit">Add to Cart</button>
           </form>
        </div>
      </div>
      <% 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
      %>
    </div>
  </div>
</section>

  
  <!-- End Lunch Dishes -->
  <!-- Dinner Dishes -->
<section id="dishGrid" data-aos="fade-down">
  <div class="container">
    <h2 class="dishGrid__title">Dinner</h2>
    <div class="dishGrid__wrapper">
      <%
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");
            String query = "SELECT FOOD_ID, FOOD_NAME, FOOD_PRICE, FOOD_IMG FROM FOODS WHERE FOOD_CATEGORY = 'Dinner'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int foodId = rs.getInt("FOOD_ID");
                String foodName = rs.getString("FOOD_NAME");
                int foodPrice = rs.getInt("FOOD_PRICE");
                String foodImg = rs.getString("FOOD_IMG");
      %>
      <div class="dishGrid__item" data-product-id="<%= foodId %>" data-product-name="<%= foodName %>" data-product-price="<%= foodPrice %>">
        <div class="dishGrid_item_img">
          <img src="../<%= foodImg %>" alt="<%= foodName %>">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title"><%= foodName %></h3>
          <div class="dishGrid_item_stars">
              <img src="../Images/3star.png" alt="3 star">
          </div>
          <h3 class="dishGrid_item_price">₹<%= foodPrice %></h3>
          <form action="/AddToCartServlet" method="post">
              <input type="hidden" name="foodId" value="<%= foodId %>">
          <button class="top_menu_btn" type="submit">Add to Cart</button>
           </form>
        </div>
      </div>
      <% 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
      %>
    </div>
  </div>
</section>
 
  <!-- End Dinner Dishes -->
  
 <%@include file="footer.jsp" %>
 <!-- Sliding Cart Section -->
    <div id="slidingCart" class="sliding-cart">
        <div class="cart-header">
            <h3><b>Your Cart</b></h3>
            <span class="cart-close" onclick="closeCart()">&times;</span>
        </div>
        <div class="cart-container">
            <%
                HttpSession ses = request.getSession();
                Cart cart = (Cart) ses.getAttribute("cart");
                if (cart != null && !cart.getItems().isEmpty()) {
                    for (CartItem item : cart.getItems()) {
            %>
                        <div class="cart-item">
                            <img src="../<%= item.getImage() %>" alt="<%= item.getName() %>">
                            <div>
                                <h3><%= item.getName() %></h3>
                                <p>Price: ₹<%= item.getPrice() %></p>
                            </div>
                           <form action="/CartServlet" method="post">
                                <input type="hidden" name="foodId" value="<%= item.getId() %>">
                                <button name="action" value="remove" class="remove-btn">Remove</button>
                                <button name="action" value="increase" class="plus">+</button>
                                <button name="action" value="decrease" class="minus">-</button>
                            </form>
                        </div>
            <%
                    }
                } else {
            %>
                    <p>Your cart is empty!</p>
            <%
                }
            %>
            <div class="total">
                <h3>Total: ₹<%= cart != null ? cart.getTotal() : 0 %></h3>
                <form action="/CheckoutServlet" method="post">
                    <button type="submit">Checkout</button>
                </form>
            </div>
        </div>
    </div>

  <!-- aos script -->
  <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
  <!-- custom script -->
  <script src="../Js/main.js"></script>
  
    <script>
        // Open the sliding cart
        function openCart() {
            document.getElementById('slidingCart').classList.add('open');
        }

        // Close the sliding cart
        function closeCart() {
            document.getElementById('slidingCart').classList.remove('open');
            
            
            // Function to check for query parameters
    function getQueryParam(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    // Check if 'cartOpen' parameter is present and open the cart
    if (getQueryParam('cartOpen') === 'true') {
        openCart(); // Call the existing openCart function
    }
        }
    </script>

</body>

</html>