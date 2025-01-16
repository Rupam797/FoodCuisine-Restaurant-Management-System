
package cartpack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ConfirmationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
Cart cart = (Cart) session.getAttribute("cart");

// Example data (you should retrieve these values from the form or payment process)
String name = request.getParameter("name");
String phone = request.getParameter("phone");
String amount = request.getParameter("amount");

// Set session attributes
session.setAttribute("name", name);
session.setAttribute("phone", phone);
session.setAttribute("amount", amount);
session.setAttribute("cartItems", cart.getItems()); // assuming Cart has a getItems() method to get the list of items

// Redirect to confirmation page
response.sendRedirect("/Dynamic/confirmation.jsp");

    }
}
