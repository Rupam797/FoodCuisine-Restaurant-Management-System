<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rms.model.Cart, com.rms.model.CartItem" %>
<%@ page import="java.util.*" %>
<%
    if (request.getAttribute("breakfast") == null) {
        response.sendRedirect(request.getContextPath() + "/menu");
        return;
    }
%>

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
                            <li><a class="nav__link" href="${pageContext.request.contextPath}/menu">Menu</a></li>
                            <li><a class="nav__link" href="../Dynamic/about.jsp">About</a></li>
                            <li><a class="nav__link" href="../Dynamic/contact.jsp">Contact</a></li>
                            <li><a class="nav__link" href="../Dynamic/booking.jsp">Book Table</a></li>
                            <li>
                                <div class="icon-cart" onclick="openCart()" style="position: relative;">
                                    <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 4h1.5L9 16m0 0h8m-8 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4Zm8 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4Zm-8.5-3h9.25L19 7H7.312"/>
                                    </svg>
                                    <span id="cartBadge" class="cart-badge"><%= (session.getAttribute("cart") != null) ? ((com.rms.model.Cart) session.getAttribute("cart")).getItemCount() : 0 %></span>
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

 <!-- Start Top Dishes -->
 <section id="dishGrid" data-aos="fade-down">
  <div class="container">
       <h1 class="page__title__text">Explore Our Menu</h1>
    <h2 class="dishGrid__title">Top Dishes</h2>
    <div class="dishGrid__wrapper">
      <c:forEach var="food" items="${topDishes}">
      <div class="dishGrid__item" data-product-id="${food.foodId}" data-product-name="${food.foodName}" data-product-price="${food.foodPrice}">
        <div class="dishGrid_item_img">
          <img src="../${food.foodImg}" alt="${food.foodName}">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title">${food.foodName}</h3>
          <p class="dishGrid_item_desc">${food.foodDesc}</p>
          <div class="dishGrid_item_footer">
              <div class="dishGrid_item_price_block">
                  <h3 class="dishGrid_item_price">₹${food.foodPrice}</h3>
                  <span class="dishGrid_item_tax">Inclusive of all taxes</span>
              </div>
              <form action="${pageContext.request.contextPath}/AddToCartServlet" method="post" style="margin: 0;">
                  <input type="hidden" name="foodId" value="${food.foodId}">
                  <button class="top_menu_btn" type="submit">
                      <i class="fa-solid fa-bag-shopping" style="margin-right: 8px;"></i>Add to Cart
                  </button>
              </form>
          </div>
        </div>
      </div>
      </c:forEach>
    </div>
  </div>
</section>
<!-- End Top Dishes -->

 <!-- Breakfast Dishes -->
 <section id="dishGrid" data-aos="fade-down">
  <div class="container">
    <h2 class="dishGrid__title">Breakfast</h2>
    <div class="dishGrid__wrapper">
      <c:forEach var="food" items="${breakfast}">
      <div class="dishGrid__item" data-product-id="${food.foodId}" data-product-name="${food.foodName}" data-product-price="${food.foodPrice}">
        <div class="dishGrid_item_img">
          <img src="../${food.foodImg}" alt="${food.foodName}">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title">${food.foodName}</h3>
          <p class="dishGrid_item_desc">${food.foodDesc}</p>
          <div class="dishGrid_item_footer">
              <div class="dishGrid_item_price_block">
                  <h3 class="dishGrid_item_price">₹${food.foodPrice}</h3>
                  <span class="dishGrid_item_tax">Inclusive of all taxes</span>
              </div>
              <form action="${pageContext.request.contextPath}/AddToCartServlet" method="post" style="margin: 0;">
                  <input type="hidden" name="foodId" value="${food.foodId}">
                  <button class="top_menu_btn" type="submit">
                      <i class="fa-solid fa-bag-shopping" style="margin-right: 8px;"></i>Add to Cart
                  </button>
              </form>
          </div>
        </div>
      </div>
      </c:forEach>
    </div>
  </div>
</section>
<!-- End Breakfast Dishes -->

<!-- Lunch Dishes -->
 <section id="dishGrid" data-aos="fade-down">
  <div class="container">
    <h2 class="dishGrid__title">Lunch</h2>
    <div class="dishGrid__wrapper">
      <c:forEach var="food" items="${lunch}">
      <div class="dishGrid__item" data-product-id="${food.foodId}" data-product-name="${food.foodName}" data-product-price="${food.foodPrice}">
        <div class="dishGrid_item_img">
          <img src="../${food.foodImg}" alt="${food.foodName}">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title">${food.foodName}</h3>
          <p class="dishGrid_item_desc">${food.foodDesc}</p>
          <div class="dishGrid_item_footer">
              <div class="dishGrid_item_price_block">
                  <h3 class="dishGrid_item_price">₹${food.foodPrice}</h3>
                  <span class="dishGrid_item_tax">Inclusive of all taxes</span>
              </div>
              <form action="${pageContext.request.contextPath}/AddToCartServlet" method="post" style="margin: 0;">
                  <input type="hidden" name="foodId" value="${food.foodId}">
                  <button class="top_menu_btn" type="submit">
                      <i class="fa-solid fa-bag-shopping" style="margin-right: 8px;"></i>Add to Cart
                  </button>
              </form>
          </div>
        </div>
      </div>
      </c:forEach>
    </div>
  </div>
</section>
<!-- End Lunch Dishes -->

<!-- Dinner Dishes -->
<section id="dishGrid" data-aos="fade-down">
  <div class="container">
    <h2 class="dishGrid__title">Dinner</h2>
    <div class="dishGrid__wrapper">
      <c:forEach var="food" items="${dinner}">
      <div class="dishGrid__item" data-product-id="${food.foodId}" data-product-name="${food.foodName}" data-product-price="${food.foodPrice}">
        <div class="dishGrid_item_img">
          <img src="../${food.foodImg}" alt="${food.foodName}">
        </div>
        <div class="dishGrid_item_info">
          <h3 class="dishGrid_item_title">${food.foodName}</h3>
          <p class="dishGrid_item_desc">${food.foodDesc}</p>
          <div class="dishGrid_item_footer">
              <div class="dishGrid_item_price_block">
                  <h3 class="dishGrid_item_price">₹${food.foodPrice}</h3>
                  <span class="dishGrid_item_tax">Inclusive of all taxes</span>
              </div>
              <form action="${pageContext.request.contextPath}/AddToCartServlet" method="post" style="margin: 0;">
                  <input type="hidden" name="foodId" value="${food.foodId}">
                  <button class="top_menu_btn" type="submit">
                      <i class="fa-solid fa-bag-shopping" style="margin-right: 8px;"></i>Add to Cart
                  </button>
              </form>
          </div>
        </div>
      </div>
      </c:forEach>
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
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart != null && !cart.getItems().isEmpty()) {
                    for (CartItem item : cart.getItems()) {
            %>
                        <div class="cart-item">
                            <img src="../<%= item.getImage() %>" alt="<%= item.getName() %>">
                            <div>
                                <h3><%= item.getName() %></h3>
                                <p>Price: ₹<%= item.getPrice() %></p>
                            </div>
                           <form action="${pageContext.request.contextPath}/CartServlet" method="post">
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
                <form action="${pageContext.request.contextPath}/CheckoutServlet" method="post">
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
        }

        // Update the cart badge count
        function updateCartBadge(count) {
            const badge = document.getElementById('cartBadge');
            if (badge) {
                badge.textContent = count;
                if (count > 0) {
                    badge.style.display = 'flex';
                } else {
                    badge.style.display = 'none';
                }
            }
        }

        // Rebuild the sliding cart HTML from JSON data
        function renderCart(data) {
            const container = document.querySelector('#slidingCart .cart-container');
            if (!container) return;

            let html = '';
            if (data.items && data.items.length > 0) {
                data.items.forEach(function(item) {
                    html += '<div class="cart-item">';
                    html += '  <img src="' + item.image + '" alt="' + item.name + '">';
                    html += '  <div>';
                    html += '    <h3>' + item.name + '</h3>';
                    html += '    <p>Price: ₹' + item.price + ' × ' + item.quantity + '</p>';
                    html += '  </div>';
                    html += '  <div class="cart-item-actions">';
                    html += '    <button class="minus" onclick="cartAction(' + item.id + ', \'decrease\')">−</button>';
                    html += '    <span class="cart-item-qty">' + item.quantity + '</span>';
                    html += '    <button class="plus" onclick="cartAction(' + item.id + ', \'increase\')">+</button>';
                    html += '    <button class="remove-btn" onclick="cartAction(' + item.id + ', \'remove\')">✕</button>';
                    html += '  </div>';
                    html += '</div>';
                });
            } else {
                html += '<p class="cart-empty-msg">Your cart is empty!</p>';
            }

            html += '<div class="total">';
            html += '  <h3>Total: ₹' + data.total + '</h3>';
            html += '  <form action="${pageContext.request.contextPath}/CheckoutServlet" method="post">';
            html += '    <button type="submit">Checkout</button>';
            html += '  </form>';
            html += '</div>';

            container.innerHTML = html;
            updateCartBadge(data.itemCount);
        }

        // AJAX Add to Cart
        function addToCart(foodId, btn) {
            // Visual feedback
            const originalText = btn.innerHTML;
            btn.innerHTML = '<i class="fa-solid fa-check" style="margin-right: 8px;"></i>Added!';
            btn.disabled = true;
            btn.style.backgroundColor = '#1e502f';

            fetch('${pageContext.request.contextPath}/AddToCartServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: 'foodId=' + foodId
            })
            .then(function(res) { return res.json(); })
            .then(function(data) {
                renderCart(data);
                openCart();
                // Reset button after 1.2 seconds
                setTimeout(function() {
                    btn.innerHTML = originalText;
                    btn.disabled = false;
                    btn.style.backgroundColor = '';
                }, 1200);
            })
            .catch(function(err) {
                console.error('Add to cart failed:', err);
                btn.innerHTML = originalText;
                btn.disabled = false;
                btn.style.backgroundColor = '';
            });
        }

        // AJAX Cart Action (remove, increase, decrease)
        function cartAction(foodId, action) {
            fetch('${pageContext.request.contextPath}/CartServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: 'foodId=' + foodId + '&action=' + action
            })
            .then(function(res) { return res.json(); })
            .then(function(data) {
                renderCart(data);
            })
            .catch(function(err) {
                console.error('Cart action failed:', err);
            });
        }

        // On page load: intercept all add-to-cart forms and set badge visibility
        document.addEventListener("DOMContentLoaded", function() {
            // Replace all add-to-cart form submissions with AJAX
            document.querySelectorAll('form[action$="/AddToCartServlet"]').forEach(function(form) {
                form.addEventListener('submit', function(e) {
                    e.preventDefault();
                    var foodId = form.querySelector('input[name="foodId"]').value;
                    var btn = form.querySelector('button[type="submit"]');
                    addToCart(foodId, btn);
                });
            });

            // Set initial badge visibility
            var badge = document.getElementById('cartBadge');
            if (badge && parseInt(badge.textContent) === 0) {
                badge.style.display = 'none';
            }

            // Check if 'cartOpen' parameter is present and open the cart on page load
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('cartOpen') === 'true') {
                openCart();
            }
        });
    </script>

</body>

</html>