package com.rms.controller.menu;

import com.rms.dao.FoodDAO;
import com.rms.model.Cart;
import com.rms.model.CartItem;
import com.rms.model.Food;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Adds a food item to the session cart.
 * Replaces: cartpack.AddToCartServlet
 */
public class AddToCartServlet extends HttpServlet {

    private final FoodDAO foodDAO = new FoodDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int foodId = Integer.parseInt(request.getParameter("foodId"));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        Food food = foodDAO.getFoodById(foodId);

        if (food != null) {
            CartItem item = new CartItem(food.getFoodId(), food.getFoodName(),
                    food.getFoodPrice(), food.getFoodImg(), 1);
            cart.addItem(item);
        }

        response.sendRedirect(request.getContextPath() + "/menu");
    }
}
