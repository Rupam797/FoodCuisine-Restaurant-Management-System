package com.rms.controller.menu;

import com.rms.dao.OrderDAO;
import com.rms.model.Cart;
import com.rms.model.CartItem;
import com.rms.model.Order;
import com.rms.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller: Processes payment, saves order, sends confirmation.
 * Replaces: cartpack.PaymentServlet
 */
public class PaymentServlet extends HttpServlet {

    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.getWriter().println("<h3>Your cart is empty!</h3>");
            return;
        }

        String customerName = request.getParameter("customer-name");
        String phoneNumber = request.getParameter("phone-number");
        int totalAmount = cart.getTotal();

        // Build food details string
        List<CartItem> items = cart.getItems();
        StringBuilder foodDetails = new StringBuilder();
        for (CartItem item : items) {
            foodDetails.append("ID: ").append(item.getId())
                       .append(", Name: ").append(item.getName())
                       .append(", Qty: ").append(item.getQuantity())
                       .append(", Price: ").append(item.getPrice())
                       .append("; ");
        }

        // Save order to DB
        Order order = new Order(customerName, phoneNumber, foodDetails.toString(), totalAmount, "Paid");
        boolean saved = orderDAO.addOrder(order);

        if (saved) {
            // Store for confirmation page
            session.setAttribute("customerName", customerName);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("totalAmount", totalAmount);
            session.setAttribute("paymentStatus", "Paid");
            session.setAttribute("foodDetails", foodDetails.toString());

            // Send email
            EmailUtil.sendOrderConfirmation(customerName, phoneNumber, foodDetails.toString(), totalAmount);

            // Clear cart
            session.removeAttribute("cart");

            response.sendRedirect(request.getContextPath() + "/Dynamic/confirmation.jsp");
        } else {
            response.getWriter().println("<h3>Error processing your payment. Please try again.</h3>");
        }
    }
}
