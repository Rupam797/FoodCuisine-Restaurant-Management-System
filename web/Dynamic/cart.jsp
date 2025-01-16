<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cartpack.CartItem"%>
<%@page import="cartpack.Cart"%>
<%@page import="java.util.*, javax.servlet.http.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart | Food Cuisine</title>
    <link rel="stylesheet" href="../Styles/reset.css">>
    <link rel="stylesheet" href="../Styles/components.css">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <link rel="stylesheet" href="../Styles/cartcss.css">
    
</head>
<body>
    <!-- Sliding Cart Section -->
    <div id="slidingCart" class="sliding-cart">
        <div class="cart-header">
            <h3>Your Cart</h3>
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
                                <button name="action" value="remove">Remove</button>
                                <button name="action" value="increase">+</button>
                                <button name="action" value="decrease">-</button>
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
    
    <script>
        // Open the sliding cart
        function openCart() {
            document.getElementById('slidingCart').classList.add('open');
        }

        // Close the sliding cart
        function closeCart() {
            document.getElementById('slidingCart').classList.remove('open');
        }
    </script>
    
</body>
</html>
