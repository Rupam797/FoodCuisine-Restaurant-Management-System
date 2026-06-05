package com.rms.controller.menu;

import com.rms.dao.FoodDAO;
import com.rms.model.Cart;
import com.rms.model.CartItem;
import com.rms.model.Food;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller: Adds a food item to the session cart.
 * Supports both standard form POST (redirect) and AJAX (JSON response).
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

        // If AJAX request, return JSON instead of redirecting
        String xRequestedWith = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(buildCartJson(cart, request.getContextPath()));
            out.flush();
        } else {
            response.sendRedirect(request.getContextPath() + "/menu?cartOpen=true");
        }
    }

    private String buildCartJson(Cart cart, String contextPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"itemCount\":").append(cart.getItemCount()).append(",");
        sb.append("\"total\":").append(cart.getTotal()).append(",");
        sb.append("\"items\":[");
        boolean first = true;
        for (CartItem item : cart.getItems()) {
            if (!first) sb.append(",");
            first = false;
            sb.append("{");
            sb.append("\"id\":").append(item.getId()).append(",");
            sb.append("\"name\":\"").append(escapeJson(item.getName())).append("\",");
            sb.append("\"price\":").append(item.getPrice()).append(",");
            sb.append("\"image\":\"").append(escapeJson(contextPath + "/" + item.getImage())).append("\",");
            sb.append("\"quantity\":").append(item.getQuantity()).append(",");
            sb.append("\"subtotal\":").append(item.getSubtotal());
            sb.append("}");
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
