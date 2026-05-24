package com.rms.controller.menu;

import com.rms.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Handles checkout — forwards to payment page.
 * Replaces: cartpack.CheckoutServlet
 */
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        response.sendRedirect(request.getContextPath() + "/Dynamic/payment.jsp");
    }
}
