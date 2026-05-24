package com.rms.controller.menu;

import com.rms.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Handles cart actions (remove, increase, decrease quantity).
 * Replaces: cartpack.CartServlet
 */
public class CartActionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        response.sendRedirect(request.getContextPath() + "/menu");
    }
}
