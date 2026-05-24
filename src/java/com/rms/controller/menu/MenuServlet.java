package com.rms.controller.menu;

import com.rms.dao.FoodDAO;
import com.rms.model.Food;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller: Loads food items from DB and forwards to menu.jsp.
 * This is NEW — eliminates all DB code from menu.jsp.
 */
public class MenuServlet extends HttpServlet {

    private final FoodDAO foodDAO = new FoodDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Food> topDishes = foodDAO.getFoodsByCategory("Top Dishes");
        List<Food> breakfast = foodDAO.getFoodsByCategory("Breakfast");
        List<Food> lunch = foodDAO.getFoodsByCategory("Lunch");
        List<Food> dinner = foodDAO.getFoodsByCategory("Dinner");

        request.setAttribute("topDishes", topDishes);
        request.setAttribute("breakfast", breakfast);
        request.setAttribute("lunch", lunch);
        request.setAttribute("dinner", dinner);

        request.getRequestDispatcher("/Dynamic/menu.jsp").forward(request, response);
    }
}
