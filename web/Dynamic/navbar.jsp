<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sess = request.getSession(false); // Retrieve the session
    String username = (sess != null) ? (String) sess.getAttribute("username") : null;
    String userEmail = (sess != null) ? (String) sess.getAttribute("userEmail") : null;
    String profilePic = (sess != null) ? (String) sess.getAttribute("profilePic") : null; // Profile picture path
%>

<!-- Toast Notification System Assets -->
<link rel="stylesheet" href="../Styles/toast.css">
<script src="../Js/toast.js"></script>

<%
    // Check for session flash messages (for toast notifications)
    if (sess != null) {
        String flashMessage = (String) sess.getAttribute("toastMessage");
        String flashType = (String) sess.getAttribute("toastType");
        if (flashMessage != null) {
            sess.removeAttribute("toastMessage");
            sess.removeAttribute("toastType");
%>
            <script>
                document.addEventListener("DOMContentLoaded", function() {
                    showToast("<%= flashMessage %>", "<%= flashType != null ? flashType : "info" %>");
                });
            </script>
<%
        }
    }
%>


<!-- Nav Section -->
<div class="nav">
  <div class="container">
    <div class="nav__wrapper">
      <a href="../Dynamic/Home.jsp" class="logo">
        <img src="../Images/logo.jpeg" alt="Food cuisine">
      </a>
      <nav>
        <div class="nav__icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
            stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
            class="feather feather-menu">
            <line x1="3" y1="12" x2="21" y2="12" />
            <line x1="3" y1="6" x2="21" y2="6" />
            <line x1="3" y1="18" x2="21" y2="18" />
          </svg>
        </div>
        <div class="nav__bgOverlay"></div>
        <ul class="nav__list">
          <div class="nav__close">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
              class="feather feather-x">
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

            <% if (username != null && userEmail != null) { %>
                <!-- Profile Dropdown Menu -->
                <li class="profile-menu">
                  <button class="nav__link profile-button" onclick="toggleDropdown()">
                    <i class="fa-solid fa-user fa-bounce"></i> <%= username %></button>
                  <div class="dropdown">
                    <div class="profile-info">
                     <img src="${pageContext.request.contextPath}/ProfilePic?email=<%= userEmail %>" 
                        alt="Profile" 
                        class="profile-pic-large">

                      <p class="username">Hi, <%= username %></p>
                      <p class="email"><%= userEmail %></p>
                    </div>
                    <ul>
                      <li><a href="../Dynamic/EditProfile.jsp"><i class="fa-solid fa-user-pen"></i>&nbsp;&nbsp;Edit Profile</a></li>
                      <li><a href="../Dynamic/Complain.jsp"><i class="fa-solid fa-clipboard"></i>&nbsp;&nbsp;Complain Register</a></li>
                      <li><a href="../Dynamic/Feedback.jsp"><i class="fa-solid fa-comment"></i>&nbsp;&nbsp;Feedback</a></li>
                      <li><a href="../Dynamic/contact.jsp"><i class="fa-solid fa-address-book"></i>&nbsp;&nbsp;Contact Us</a></li>
                      <li><a href="${pageContext.request.contextPath}/Logout"><i class="fa-solid fa-right-from-bracket"></i>&nbsp;&nbsp;Logout</a></li>
                    </ul>
                  </div>
                </li>
            <% } else { %>
                <li><a class="nav__link" href="../Dynamic/login.jsp">Log in</a></li>
                <li><a class="nav__link" href="../Dynamic/signup.jsp">Sign Up</a></li>
            <% } %>
          </div>
        </ul>
      </nav>
    </div>
  </div>
</div>
<!-- End Nav Section -->
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