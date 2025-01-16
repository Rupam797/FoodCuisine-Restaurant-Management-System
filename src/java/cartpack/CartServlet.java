



package cartpack;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        
        switch (action) {
            case "remove":
                cart.removeItem(foodId);
                break;
            case "increase":
                cart.increaseQuantity(foodId);
                break;
            case "decrease":
                cart.decreaseQuantity(foodId);
                break;
        }

        // Redirect back to the cart page to reflect the changes
        response.sendRedirect("/Dynamic/menu.jsp");
    }
}
