package com.rms.controller.admin;

import com.rms.dao.OrderDAO;
import com.rms.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller: Admin order viewing.
 * Replaces: DB code in OrderDetails.jsp
 */
public class ManageOrdersServlet extends HttpServlet {

    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> orders = orderDAO.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/Dynamic/OrderDetails.jsp").forward(request, response);
    }
}
