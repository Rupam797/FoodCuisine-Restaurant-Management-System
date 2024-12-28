<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="../Images/favicon.ico" type="image/x-icon">
  <title>Booking | Food Cuisine</title>
  <link rel="stylesheet" href="../Styles/reset.css">
  <link rel="stylesheet" href="../Styles/globalStyles.css">
  <link rel="stylesheet" href="../Styles/components.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
         integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
         crossorigin="anonymous" 
         referrerpolicy="no-referrer" />
  <!-- aos library css  -->
  <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
  <link rel="stylesheet" href="../Styles/profile.css">
</head>

<body>
  <%@include file="navbar.jsp" %>
  <!-- Booking Section -->
  <section id="form" data-aos="fade-up">
    <div class="container">
      <h3 class="form__title">Book Table</h3>
      <div class="form__wrapper">
        <form name="booking" action="/TableBooking" method="post" netlify>
          <div class="form__group">
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="FirstName" required>
          </div>
          <div class="form__group">
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="LastName" required>
          </div>
          <div class="form__group">
            <label for="email">Email</label>
            <input type="email" id="email" name="Email" required>
          </div>
          <div class="form__group">
            <label for="tableType">Table Type</label>
            <select name="TableType" id="tableType" required>
              <option selected disabled>Choose</option>
              <option value="small" name="TableType">Small(2 persons)</option>
              <option value="medium" name="TableType">Small(4 persons)</option>
              <option value="large" name="TableType">large(6 persons)</option>
            </select>
          </div>
          <div class="form__group">
            <label for="guestNumber">Phone Number</label>
            <input type="number" id="guestNumber" name="GuestNumber"  required>
          </div>
          <div class="form__group">
            <label for="placement">Placement</label>
            <select name="Placement" id="placement">
              <option selected disabled>Choose</option>
              <option value="outdoor" name="Placement">Outdoor</option>
              <option value="indoor" name="Placement">Indoor</option>
              <option value="rooftop" name="Placement">Rooftop</option>
            </select>
          </div>
          <div class="form__group">
            <label for="date">Date</label>
            <input type="date" id="date" name="Date" required>
          </div>
          <div class="form__group">
            <label for="startTime">Starting Time</label>
            <input type="time" id="startTime" name="StartTime" required>
          </div>
          <div class="form__group">
            <label for="endTime">Ending Time</label>
            <input type="time" id="endTime" name="EndTime" required>
          </div>
          <div class="form__group form__group__full">
            <label for="note">Note</label>
            <textarea name="Note" id="note" rows="4" required></textarea>
          </div>
          <button type="submit" class="btn primary-btn">Book Table</button>
        </form>
      </div>
    </div>
  </section>
  <!-- End Booking Section -->
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
