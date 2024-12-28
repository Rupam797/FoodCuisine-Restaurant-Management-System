<%@ page import="javax.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
  <title>Food Cuisine</title>
  <link rel="stylesheet" href="../Styles/reset.css">
  <link rel="stylesheet" href="../Styles/globalStyles.css">
  <link rel="stylesheet" href="../Styles/components.css">
  <!-- aos library css  -->
  <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
  <link rel="stylesheet" href="../Styles/home.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
         integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
         crossorigin="anonymous" 
         referrerpolicy="no-referrer" />
   <link rel="stylesheet" href="../Styles/profile.css">


   
</head>

<%--
    HttpSession sess = request.getSession(false); // Retrieve the existing session
    String username = (String) sess.getAttribute("username");
    String userEmail = (String) sess.getAttribute("userEmail");
--%>
<%--
<p>Your age: <%= session.getAttribute("userAge") %></p>
<p>Your gender: <%= session.getAttribute("userGender") %></p>
<h1>Welcome, <%= session.getAttribute("username") %></h1>--%>




<% 
    String loginMessage = (String) request.getAttribute("loginMessage");
    if (loginMessage != null) {
%>
    <script>
        alert("<%= loginMessage %>");
    </script>
<% 
    }
%>




<body>
 
</div>


   <%@include file="navbar.jsp" %>
  <!-- Hero Section -->
  <section id="hero">
    <div class="container">
      <div class="hero__wrapper">
        <div class="hero__left" data-aos="fade-left">
          <div class="hero__left__wrapper">

            <h1 class="hero__heading">The flavor of tradition</h1>
            <p class="hero__info">
              We are a multi-cuisine restaurant offering a wide variety of food experience in both casual and fine dining environment.
            </p>
            <div class="button__wrapper">
              <a href="../Dynamic/menu.jsp" class="btn primary-btn">Explore Menu</a>
              <a href="../Dynamic/booking.jsp" class="btn">Book Table</a>
            </div>
          </div>
        </div>
        <div class="hero__right" data-aos="fade-right">
          <div class="hero__imgWrapper">
            <img src="../Images/heroImg.png">
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Hero Section -->
  <!-- Store Info Section -->
  <section id="storeInfo" data-aos="fade-up">
    <div class="container">
      <div class="storeInfo__wrapper">
        <div class="storeInfo__item">
          <div class="storeInfo__icon">
            <img src="../Images/wall-clock-icon.svg" alt="clock icon">
          </div>
          <h3 class="storeInfo__title">
            10 AM - 11 PM
          </h3>
          <p class="storeInfo__text">
            Opening Hour
          </p>
        </div>
        <div class="storeInfo__item">
          <div class="storeInfo__icon">
            <img src="../Images/address-icon.svg" alt="clock icon">
          </div>
          <h3 class="storeInfo__title">
            Salt-Lake, Kolkata
          </h3>
          <p class="storeInfo__text">
            Address
          </p>
        </div>
        <div class="storeInfo__item">
          <div class="storeInfo__icon">
            <img src="../Images/phone-icon.svg" alt="clock icon">
          </div>
          <h3 class="storeInfo__title">
            +91 9999999999
          </h3>
          <p class="storeInfo__text">
            Call Now
          </p>
        </div>
      </div>
    </div>
  </section>
  <!-- End Store Info Section -->
  <!-- Our Specials Section -->
  <section id="ourSpecials" data-aos="fade-up">
    <div class="container">
      <div class="ourSpecials__wrapper">
        <div class="ourSpecials__left">
          <div class="ourSpecials__item">
            <div class="ourSpecials__item__img">
              <img src="../Images/food-3.png" alt="food img">
            </div>
            <h2 class="ourSpecials__item__title">
              Bengali Thali (Non veg)
            </h2>
            <h3 class="ourSpecials__item__price">
              ₹1150
            </h3>
            <div class="ourSpecials__item__stars">
              <img src="../Images/3star.png" alt="3 stars">
            </div>
            <p class="ourSpecials__item__text">
              Bengali Thali, both vegetarian and non-vegetarian, are traditional meals that showcase India’s diverse flavors on a single platter. Veg Thalis include items like curries, dal, rice, and dessert, while non-veg Thalis add meat or seafood dishes. Each Thali offers a balanced and flavorful experience, representing regional culinary richness in a single meal.
            </p>
          </div>
          <div class="ourSpecials__item">
            <div class="ourSpecials__item__img">
              <img src="../Images/food-1.png" alt="food img">
            </div>
            <h2 class="ourSpecials__item__title">
              Chinese Food bowls 
            </h2>
            <h3 class="ourSpecials__item__price">
              ₹1250
            </h3>
            <div class="ourSpecials__item__stars">
              <img src="../Images/3star.png" alt="3 stars">
            </div>
            <p class="ourSpecials__item__text">
              Chinese food bowls are hearty meals featuring a base of rice or noodles, topped with stir-fried or steamed vegetables, proteins like chicken, beef, pork, or tofu, and finished with rich, flavorful sauces. Offering a balanced and satisfying taste, these bowls showcase the diversity of Chinese cuisine, from spicy Sichuan flavors to the savory tastes of Cantonese dishes.
            </p>
          </div>
        </div>
        <div class="ourSpecials__right">
          <h2 class="ourSpecials__title">
            Our Specials
          </h2>
          <p class="ourSpecials__text">
            All of our food is prepared daily at our restaurants to ensure the highest quality, freshest meals are
            delivered to our customers
          </p>
          <a href="../Dynamic/booking.jsp" class="btn primary-btn">Book Table</a>
        </div>
      </div>
    </div>
  </section>
  <!-- End Our Specials Section -->
  <!-- Top Dishes -->
  <section id="dishGrid" data-aos="fade-up">
    <div class="container">
      <h2 class="dishGrid__title">
        Top Dishes
      </h2>
      <div class="dishGrid__wrapper">
        <div class="dishGrid__item">
          <div class="dishGrid__item__img">
            <img src="../Images/biriyani.webp" alt="food img">
          </div>
          <div class="dishGrid__item__info">
            <h3 class="dishGrid__item__title">
              Mutton Biriyani
            </h3>
            <h3 class="dishGrid__item__price">₹450</h3>
            <div class="dishGrid__item__stars">
              <img src="../Images/3star.png" alt="3 star">
            </div>
          </div>
        </div>
        <div class="dishGrid__item">
          <div class="dishGrid__item__img">
            <img src="../Images/Chowmein.jpg" alt="food img">
          </div>
          <div class="dishGrid__item__info">
            <h3 class="dishGrid__item__title">
              Chowmein
            </h3>
            <h3 class="dishGrid__item__price">₹250</h3>
            <div class="dishGrid__item__stars">
              <img src="../Images/3star.png" alt="3 star">
            </div>
          </div>
        </div>
        <div class="dishGrid__item">
          <div class="dishGrid__item__img">
            <img src="../Images/momo.jpg" alt="food img">
          </div>
          <div class="dishGrid__item__info">
            <h3 class="dishGrid__item__title">
              Chicken Momo
            </h3>
            <h3 class="dishGrid__item__price">₹150</h3>
            <div class="dishGrid__item__stars">
              <img src="../Images/3star.png" alt="3 star">
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Top Dishes -->
  <!-- Discount Section -->
  <section id="discount" data-aos="fade-up">
    <div class="container">
      <div class="discount__wrapper">
        <div class="discount__images">
          <div class="discount__img1">
            <img src="../Images/food-5.png" alt="Food img">
          </div>
          <div class="discount__img2">
            <img src="../Images/pasta.jpg" alt="Food img">
          </div>
          <div class="discount__img3">
            <img src="../Images/pizza.jpg" alt="Food img">
          </div>
        </div>
        <div class="discount__info">
          <h3 class="discount__text"></h3>
          <h3 class="discount__title">Demo Dish For Discount</h3>
          <h3 class="discount__price">
            <span class="discount__oldPrice">₹450</span>
            <span class="discount__newPrice">₹350</span>
          </h3>
          <div class="discount__stars">
            <img src="../Images/3star.png" alt="3 stars">
          </div>
          <a class="btn primary-btn" href="../Dynamic/booking.jsp">Book Table</a>
        </div>
      </div>
    </div>
  </section>
  <!-- End Discount Section -->
  
<!-- Why Us section -->
<section id="whyUs">
  <div class="container">
    <div class="whyUs__wrapper">
      <div class="whyUs__left" data-aos="fade-right">
        <h2 class="whyUs__title">
          Why Choose Our Food
        </h2>
        <p class="whyUs__text">
          Quality of Service, Food, Ambiance, and Value of Money are the primary elements for choosing a restaurant.
          Food Cuisine is one of the most exquisite fine-dinning restaurant in Salt-Lake with a captivating
          view of Kolkata, perfect ambiance, and scrumptious food.
        </p>
      </div>
      <div class="whyUs__right" data-aos="fade-left">
        <div class="whyUs__items__wrapper">
          <div class="whyUs__item">
            <div class="whyUs__item__icon">
              <img src="../Images/whyUs-icon1.svg" alt="quality Food">
            </div>
            <p class="whyUs__item__text">Quality Food</p>
          </div>
          <div class="whyUs__item">
            <div class="whyUs__item__icon">
              <img src="../Images/whyUs-icon2.svg" alt="Classical taste">
            </div>
            <p class="whyUs__item__text">Classical taste</p>
          </div>
          <div class="whyUs__item">
            <div class="whyUs__item__icon">
              <img src="../Images/whyUs-icon3.svg" alt="Skilled chef">
            </div>
            <p class="whyUs__item__text">Skilled chef</p>
          </div>
          <div class="whyUs__item">
            <div class="whyUs__item__icon">
              <img src="../Images/whyUs-icon4.svg" alt="Best service">
            </div>
            <p class="whyUs__item__text">Best service</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- End Why Us section -->

<!-- Events Media -->
<section id="eventsMedia" data-aos="fade-up">
  <div class="container">
    <div class="eventsMedia__wrapper">
      <div class="eventsMedia__1">
        <img src="../Images/eventsMedia1.png" alt="events">
        <!--<a href="#" class="eventsMedia__1__playButton">
          <img src="./images/playIcon.svg" alt="play button">
        </a>-->
      </div>
      <div class="eventMedia__2">
        <img src="../Images/eventsMedia2.png" alt="events">
      </div>
    </div>
  </div>
</section>
<!-- End Events Media -->
  <!-- Events Info -->
  <section id="eventsInfo" data-aos="fade-up">
    <div class="container">
      <div class="eventsInfo__wrapper">
        <div class="eventsInfo__left">
          <div class="eventsInfo__item">
            <div class="eventsInfo__item__img">
              <img src="../Images/event-corporate.png" alt="corporate events">
            </div>
            <div class="eventsInfo__item__info">
              <h2 class="eventsInfo__item__title">Corporate Events</h2>
              <p class="eventsInfo__item__text">
                Food Cuisine is the perfect venue for your corporate events. We specialize in private parties.
              </p>
            </div>
          </div>
          <div class="eventsInfo__item">
            <div class="eventsInfo__item__img">
              <img src="../Images/event-weedings.png" alt="wedding events">
            </div>
            <div class="eventsInfo__item__info">
              <h2 class="eventsInfo__item__title">Weddings</h2>
              <p class="eventsInfo__item__text">
                Food Cuisine offers an intimately unique wedding experience set in a spectacular setting that is
                sophisticated and comfortable.
              </p>
            </div>
          </div>
        </div>
        <div class="eventsInfo__right">
          <h2 class="eventsInfo__title">Book For Events</h2>
          <p class="eventsInfo__text">Book your private event or corporate function at Food cuisine to experience a
            truly unique experience.</p>
          <a href="../Dynamic/contact.jsp" class="btn primary-btn">Contact Now</a>
        </div>
      </div>
    </div>
  </section>
  <!-- End Events Info -->
   <%@include file="footer.jsp" %>



  <!-- aos script -->
  <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
  <!-- custom script -->
  <script src="../Js/main.js"></script>
 
<script  src="../Js/alert.js"></script>

  


  
</body>

</html>