<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
  <title>About | Food Cuisine</title>
  <link rel="stylesheet" href="../Styles/reset.css">
  <link rel="stylesheet" href="../Styles/globalStyles.css">
  <link rel="stylesheet" href="../Styles/components.css">
  <!-- aos library css  -->
  <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
  <link rel="stylesheet" href="../Styles/about.css">
  <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
      integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  <link rel="stylesheet" href="../Styles/profile.css">
</head>

<body>
  <%@include file="navbar.jsp" %>
  <!-- Our Story Section -->
  <section id="ourStory" data-aos="fade-up">
    <div class="container">
      <div class="ourStory__wrapper">

        <div class="ourStory__img">
          <img src="../Images/chef2.png" alt="Food cuisine">
        </div>
        <div class="ourStory__info">
          <h3 class="ourStory__title">
            Our Story
          </h3>
          <p class="ourStory__subtitle">
            It's all started since 2024
          </p>
          <p class="ourStory__text">
            Welcome to Food Cuisine. We take pride in delivering warm, friendly service and creating mouth-watering
            culinary delights for all. Using only the freshest locally sourced ingredients, we’ll ensure you have a
            dining experience to remember.
            <br><br>
            Since 2024, we are the perfect place for a romantic meal for two, a catch-up with friends, family parties,
            business meetings, and bringing loved ones together. With comfortable surroundings, affordable prices, and
            seating for up to 65 guests, we can cater for all occasions.
          </p>
        </div>
      </div>
    </div>
  </section>
  <!-- End Our Story Section -->
  <!-- Our Goals -->
  <section id="ourGoals" data-aos="fade-down">
    <div class="container">
      <div class="ourGoals__info">
        <h3 class="ourGoals__title">
          Our Goals
        </h3>
        <p class="ourGoals__text">
          We shall sell delicious and remarkable food and beverages that meet the highest quality, freshness, and
          seasonality criteria while combining modern-creative and classic cooking traditions. By showcasing warmth,
          graciousness, efficiency, skill, professionalism, and integrity in our work, we will continually serve our
          consumers with exceptional service. To have every customer who comes through our doors leave impressed by
          their experience at Food Cuisine and excited to come back again.
        </p>
      </div>
      <div class="ourGoals__imgs__wrapper" data-aos="fade-up">
        <div class="ourGoals__img1">
          <img src="../Images/ourGoals_img1.png" alt="kitchen img">
        </div>
        <div class="ourGoals__img2">
          <img src="../Images/ourGoals_img2.png" alt="kitchen img">
        </div>
        <div class="ourGoals__img3">
          <img src="../Images/ourGoals_img3.png" alt="kitchen img">
        </div>
      </div>
    </div>
  </section>
  <!-- End Our Goals -->
<%@include file="footer.jsp" %>

  <!-- aos scripts -->
  <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
  <!-- custom scripts -->
  <script src="../Js/main.js"></script>
  <script>// Toggle the dropdown menu visibility
function toggleDropdown() {
  const dropdown = document.querySelector('.dropdown');
  dropdown.classList.toggle('show');
}

// Close the dropdown when clicking outside
window.addEventListener('click', function(e) {
  const dropdown = document.querySelector('.dropdown');
  const profileButton = document.querySelector('.profile-button');

  if (!profileButton.contains(e.target) && !dropdown.contains(e.target)) {
    dropdown.classList.remove('show');
  }
});
</script>
</body>

</html>