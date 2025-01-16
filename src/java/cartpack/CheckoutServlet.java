
package cartpack;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CheckoutServlet extends HttpServlet {
   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
        request.getSession().setAttribute("cart", cart);
    }

    // Redirect to the payment page
    response.sendRedirect("/Dynamic/payment.jsp");
}

}

