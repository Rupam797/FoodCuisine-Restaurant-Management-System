package com.rms.controller.menu;

import com.rms.model.Cart;
import com.rms.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller: Handles cart actions (remove, increase, decrease quantity).
 * Supports both standard form POST (redirect) and AJAX (JSON response).
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

