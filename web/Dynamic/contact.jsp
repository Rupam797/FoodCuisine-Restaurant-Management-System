<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
  <title>Contact | Food Cuisine</title>
  <link rel="stylesheet" href="../Styles/reset.css">
  <link rel="stylesheet" href="../Styles/globalStyles.css">
  <link rel="stylesheet" href="../Styles/components.css">
  <!-- aos library css  -->
  <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
  <!-- Add your custom css -->
  <link rel="stylesheet" href="../Styles/profile.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
         integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
         crossorigin="anonymous" 
         referrerpolicy="no-referrer" />
</head>

<body>
  <%@include file="navbar.jsp" %>
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
  
  <%@include file="footer.jsp" %>

  <!-- aos script -->
  <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
  <!-- custom script -->
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
