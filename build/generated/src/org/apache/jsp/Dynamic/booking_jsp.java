package org.apache.jsp.Dynamic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.HttpSession;

public final class booking_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/Dynamic/navbar.jsp");
    _jspx_dependants.add("/Dynamic/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("  <link rel=\"shortcut icon\" href=\"../Images/favicon.ico\" type=\"image/x-icon\">\n");
      out.write("  <title>Booking | Food Cuisine</title>\n");
      out.write("  <link rel=\"stylesheet\" href=\"../Styles/reset.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"../Styles/globalStyles.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"../Styles/components.css\">\n");
      out.write("   <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css\" \n");
      out.write("         integrity=\"sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==\" \n");
      out.write("         crossorigin=\"anonymous\" \n");
      out.write("         referrerpolicy=\"no-referrer\" />\n");
      out.write("  <!-- aos library css  -->\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://unpkg.com/aos@next/dist/aos.css\" />\n");
      out.write("  <link rel=\"stylesheet\" href=\"../Styles/profile.css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("  ");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    HttpSession sess = request.getSession(false); // Retrieve the session
    String username = (String) sess.getAttribute("username");
    String userEmail = (String) sess.getAttribute("userEmail");
    String profilePic = (String) sess.getAttribute("profilePic");// Profile picture path
    if (username != null && userEmail != null) {

      out.write('\n');
      out.write('\n');

    } else {

      out.write("\n");
      out.write("        <script>\n");
      out.write("        alert(\"Session expired or user not logged in.\");\n");
      out.write("        </script>\n");

    }

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Nav Section -->\n");
      out.write("<div class=\"nav\">\n");
      out.write("  <div class=\"container\">\n");
      out.write("    <div class=\"nav__wrapper\">\n");
      out.write("      <a href=\"../Dynamic/Home.jsp\" class=\"logo\">\n");
      out.write("        <img src=\"../Images/logo.jpeg\" alt=\"Food cuisine\">\n");
      out.write("      </a>\n");
      out.write("      <nav>\n");
      out.write("        <div class=\"nav__icon\">\n");
      out.write("          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\"\n");
      out.write("            stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"\n");
      out.write("            class=\"feather feather-menu\">\n");
      out.write("            <line x1=\"3\" y1=\"12\" x2=\"21\" y2=\"12\" />\n");
      out.write("            <line x1=\"3\" y1=\"6\" x2=\"21\" y2=\"6\" />\n");
      out.write("            <line x1=\"3\" y1=\"18\" x2=\"21\" y2=\"18\" />\n");
      out.write("          </svg>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"nav__bgOverlay\"></div>\n");
      out.write("        <ul class=\"nav__list\">\n");
      out.write("          <div class=\"nav__close\">\n");
      out.write("            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\"\n");
      out.write("              stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"\n");
      out.write("              class=\"feather feather-x\">\n");
      out.write("              <line x1=\"18\" y1=\"6\" x2=\"6\" y2=\"18\" />\n");
      out.write("              <line x1=\"6\" y1=\"6\" x2=\"18\" y2=\"18\" />\n");
      out.write("            </svg>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"nav_list_wrapper\">\n");
      out.write("            <li><a class=\"nav__link\" href=\"../Dynamic/Home.jsp\">Home</a></li>\n");
      out.write("            <li><a class=\"nav__link\" href=\"../Dynamic/menu.jsp\">Menu</a></li>\n");
      out.write("            <li><a class=\"nav__link\" href=\"../Dynamic/about.jsp\">About</a></li>\n");
      out.write("            <li><a class=\"nav__link\" href=\"../Dynamic/contact.jsp\">Contact</a></li>\n");
      out.write("            <li><a class=\"nav__link\" href=\"../Dynamic/booking.jsp\">Book Table</a></li>\n");
      out.write("\n");
      out.write("            <!-- Profile Dropdown Menu -->\n");
      out.write("            <li class=\"profile-menu\">\n");
      out.write("              <button class=\"nav__link profile-button\" onclick=\"toggleDropdown()\">\n");
      out.write("                <i class=\"fa-solid fa-user fa-bounce\"></i> ");
      out.print( username != null ? username : "Profile" );
      out.write("</button>\n");
      out.write("              <div class=\"dropdown\">\n");
      out.write("                <div class=\"profile-info\">\n");
      out.write("                 <img src=\"");
      out.print( profilePic != null ? "../Images/" + profilePic : "../Images/profile-circle.jpg" );
      out.write("\" \n");
      out.write("                    alt=\"Profile\" \n");
      out.write("                    class=\"profile-pic-large\">\n");
      out.write("\n");
      out.write("                  <p class=\"username\">Hi, ");
      out.print( username != null ? username : "Guest" );
      out.write("</p>\n");
      out.write("                  <p class=\"email\">");
      out.print( userEmail != null ? userEmail : "Not available" );
      out.write("</p>\n");
      out.write("                </div>\n");
      out.write("                <ul>\n");
      out.write("                  <li><a href=\"../Dynamic/EditProfile.jsp\"><i class=\"fa-solid fa-user-pen\"></i>&nbsp;&nbsp;Edit Profile</a></li>\n");
      out.write("                  <li><a href=\"../Dynamic/Complain.jsp\"><i class=\"fa-solid fa-clipboard\"></i>&nbsp;&nbsp;Complain Register</a></li>\n");
      out.write("                  <li><a href=\"../Dynamic/Feedback.jsp\"><i class=\"fa-solid fa-comment\"></i>&nbsp;&nbsp;Feedback</a></li>\n");
      out.write("                  <li><a href=\"../Dynamic/contact.jsp\"><i class=\"fa-solid fa-address-book\"></i>&nbsp;&nbsp;Contact Us</a></li>\n");
      out.write("                  <li><a href=\"../Pages/index.html\"><i class=\"fa-solid fa-right-from-bracket\"></i>&nbsp;&nbsp;Logout</a></li>\n");
      out.write("                </ul>\n");
      out.write("              </div>\n");
      out.write("            </li>\n");
      out.write("          </div>\n");
      out.write("        </ul>\n");
      out.write("      </nav>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("</div>\n");
      out.write("<!-- End Nav Section -->\n");
      out.write("<script>// Toggle the dropdown menu visibility\n");
      out.write("function toggleDropdown() {\n");
      out.write("  const dropdown = document.querySelector('.dropdown');\n");
      out.write("  dropdown.classList.toggle('show');\n");
      out.write("}\n");
      out.write("\n");
      out.write("// Close the dropdown when clicking outside\n");
      out.write("window.addEventListener('click', function(e) {\n");
      out.write("  const dropdown = document.querySelector('.dropdown');\n");
      out.write("  const profileButton = document.querySelector('.profile-button');\n");
      out.write("\n");
      out.write("  if (!profileButton.contains(e.target) && !dropdown.contains(e.target)) {\n");
      out.write("    dropdown.classList.remove('show');\n");
      out.write("  }\n");
      out.write("});\n");
      out.write("</script>");
      out.write("\n");
      out.write("  <!-- Booking Section -->\n");
      out.write("  <section id=\"form\" data-aos=\"fade-up\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("      <h3 class=\"form__title\">Book Table</h3>\n");
      out.write("      <div class=\"form__wrapper\">\n");
      out.write("        <form name=\"booking\" action=\"/TableBooking\" method=\"post\" netlify>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"firstName\">First Name</label>\n");
      out.write("            <input type=\"text\" id=\"firstName\" name=\"FirstName\" required>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"lastName\">Last Name</label>\n");
      out.write("            <input type=\"text\" id=\"lastName\" name=\"LastName\" required>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"email\">Email</label>\n");
      out.write("            <input type=\"email\" id=\"email\" name=\"Email\" required>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"tableType\">Table Type</label>\n");
      out.write("            <select name=\"TableType\" id=\"tableType\" required>\n");
      out.write("              <option selected disabled>Choose</option>\n");
      out.write("              <option value=\"small\" name=\"TableType\">Small(2 persons)</option>\n");
      out.write("              <option value=\"medium\" name=\"TableType\">Small(4 persons)</option>\n");
      out.write("              <option value=\"large\" name=\"TableType\">large(6 persons)</option>\n");
      out.write("            </select>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"guestNumber\">Phone Number</label>\n");
      out.write("            <input type=\"number\" id=\"guestNumber\" name=\"GuestNumber\"  required>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"placement\">Placement</label>\n");
      out.write("            <select name=\"Placement\" id=\"placement\">\n");
      out.write("              <option selected disabled>Choose</option>\n");
      out.write("              <option value=\"outdoor\" name=\"Placement\">Outdoor</option>\n");
      out.write("              <option value=\"indoor\" name=\"Placement\">Indoor</option>\n");
      out.write("              <option value=\"rooftop\" name=\"Placement\">Rooftop</option>\n");
      out.write("            </select>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"date\">Date</label>\n");
      out.write("            <input type=\"date\" id=\"date\" name=\"Date\" required>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"startTime\">Starting Time</label>\n");
      out.write("            <input type=\"time\" id=\"startTime\" name=\"StartTime\" required>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group\">\n");
      out.write("            <label for=\"endTime\">Ending Time</label>\n");
      out.write("            <input type=\"time\" id=\"endTime\" name=\"EndTime\" required>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"form__group form__group__full\">\n");
      out.write("            <label for=\"note\">Note</label>\n");
      out.write("            <textarea name=\"Note\" id=\"note\" rows=\"4\" required></textarea>\n");
      out.write("          </div>\n");
      out.write("          <button type=\"submit\" class=\"btn primary-btn\">Book Table</button>\n");
      out.write("        </form>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </section>\n");
      out.write("  <!-- End Booking Section -->\n");
      out.write("  ");
      out.write("  <!-- Footer -->\n");
      out.write("  <footer>\n");
      out.write("    <div class=\"container\">\n");
      out.write("      <div class=\"footer__wrapper\">\n");
      out.write("        <div class=\"footer__col1\">\n");
      out.write("          <div class=\"footer__logo\">\n");
      out.write("            <img src=\"../Images/logo2.png\" alt=\"Food cuisine\">\n");
      out.write("          </div>\n");
      out.write("          <p class=\"footer__desc\">\n");
      out.write("            Fresh and delicious traditional Indian & Chinese food to delight the whole family.\n");
      out.write("          </p>\n");
      out.write("          <div class=\"footer__socials\">\n");
      out.write("            <h4 class=\"footer__socials__title\">Follow us</h4>\n");
      out.write("            <ol class=\"footer__socials__list\">\n");
      out.write("              <li>\n");
      out.write("                <a href=\"#\">\n");
      out.write("                  <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\"\n");
      out.write("                    stroke=\"currentColor\" stroke-width=\"1\" stroke-linecap=\"round\" stroke-linejoin=\"round\"\n");
      out.write("                    class=\"feather feather-facebook\">\n");
      out.write("                    <path d=\"M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z\" />\n");
      out.write("                  </svg>\n");
      out.write("                </a>\n");
      out.write("              </li>\n");
      out.write("              <li>\n");
      out.write("                <a href=\"#\">\n");
      out.write("                  <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\"\n");
      out.write("                    stroke=\"currentColor\" stroke-width=\"1\" stroke-linecap=\"round\" stroke-linejoin=\"round\"\n");
      out.write("                    class=\"feather feather-instagram\">\n");
      out.write("                    <rect x=\"2\" y=\"2\" width=\"20\" height=\"20\" rx=\"5\" ry=\"5\" />\n");
      out.write("                    <path d=\"M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z\" />\n");
      out.write("                    <line x1=\"17.5\" y1=\"6.5\" x2=\"17.51\" y2=\"6.5\" />\n");
      out.write("                  </svg>\n");
      out.write("                </a>\n");
      out.write("              </li>\n");
      out.write("              <li>\n");
      out.write("                <a href=\"#\">\n");
      out.write("                  <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\"\n");
      out.write("                    stroke=\"currentColor\" stroke-width=\"1\" stroke-linecap=\"round\" stroke-linejoin=\"round\"\n");
      out.write("                    class=\"feather feather-twitter\">\n");
      out.write("                    <path\n");
      out.write("                      d=\"M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z\" />\n");
      out.write("                  </svg>\n");
      out.write("                </a>\n");
      out.write("              </li>\n");
      out.write("            </ol>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"footer__col2\">\n");
      out.write("          <h3 class=\"footer__text__title\">\n");
      out.write("            Links\n");
      out.write("          </h3>\n");
      out.write("          <ol class=\"footer__text\">\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/Home.jsp\">Home</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/menu.jsp\">Menu</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/booking.jsp\">Book Table</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/about.jsp\">About Us</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/contact.jsp\">contact Us</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"#\">Privacy Policy</a>\n");
      out.write("            </li>\n");
      out.write("          </ol>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"footer__col3\">\n");
      out.write("          <h3 class=\"footer__text__title\">\n");
      out.write("            Support\n");
      out.write("          </h3>\n");
      out.write("          <ol class=\"footer__text\">\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/contact.jsp\">Contact</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/contact.jsp\">Support Center</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"../Dynamic/contact.jsp\">Feedback</a>\n");
      out.write("            </li>\n");
      out.write("          </ol>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"footer__col4\">\n");
      out.write("          <h3 class=\"footer__text__title\">\n");
      out.write("            Contact\n");
      out.write("          </h3>\n");
      out.write("          <ol class=\"footer__text\">\n");
      out.write("            <li>\n");
      out.write("              <a href=\"#\">+91 9999999999</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"#\">cusinefood0@gmail.com</a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("              <a href=\"#\">Salt-Lake, Kolkata, India</a>\n");
      out.write("            </li>\n");
      out.write("          </ol>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </footer>\n");
      out.write("  <div id=\"copyright\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("      <p class=\"copyright__text\">\n");
      out.write("        By continuing this page, you agree to our Terms of Service, Cookie Policy, Privacy Policy. © Food Cuisine? Ltd. | All rights reserved 2024-2025.\n");
      out.write("      <br>Made by: Rupam Giri , Amit Roy , Rupsha Maity , Souvik Kamila \n");
      out.write("      </p>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("  <!-- End Footer -->\n");
      out.write("\n");
      out.write("\n");
      out.write("  <!-- aos scripts -->\n");
      out.write("  <script src=\"https://unpkg.com/aos@next/dist/aos.js\"></script>\n");
      out.write("  <!-- custom scripts -->\n");
      out.write("  <script src=\"../Js/main.js\"></script>\n");
      out.write("   <script>// Toggle the dropdown menu visibility\n");
      out.write("function toggleDropdown() {\n");
      out.write("  const dropdown = document.querySelector('.dropdown');\n");
      out.write("  dropdown.classList.toggle('show');\n");
      out.write("}\n");
      out.write("\n");
      out.write("// Close the dropdown when clicking outside\n");
      out.write("window.addEventListener('click', function(e) {\n");
      out.write("  const dropdown = document.querySelector('.dropdown');\n");
      out.write("  const profileButton = document.querySelector('.profile-button');\n");
      out.write("\n");
      out.write("  if (!profileButton.contains(e.target) && !dropdown.contains(e.target)) {\n");
      out.write("    dropdown.classList.remove('show');\n");
      out.write("  }\n");
      out.write("});\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
