package com.rms.controller.admin;

import com.rms.dao.FoodDAO;
import com.rms.model.Food;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller: Admin food management (list, add).
 * Replaces: adminpack.AddFood and DB code in FoodDetails.jsp
 */
@MultipartConfig
public class ManageFoodsServlet extends HttpServlet {

    private final FoodDAO foodDAO = new FoodDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Food> foods = foodDAO.getAllFoods();
        request.setAttribute("foods", foods);
        request.getRequestDispatcher("/Dynamic/FoodDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int foodId = Integer.parseInt(request.getParameter("foodId"));
            foodDAO.deleteFood(foodId);
            response.sendRedirect(request.getContextPath() + "/admin/foods");
            return;
        }

        // Default: Add food
        int id = Integer.parseInt(request.getParameter("fid"));
        String name = request.getParameter("foodname");
        int price = Integer.parseInt(request.getParameter("foodprice"));
        String category = request.getParameter("foodcategory");

        // Handle image upload
        Part avatarImage = request.getPart("foodimg");
        String avatarFileName = name + getFileExtension(avatarImage.getSubmittedFileName());

        String uploadPath = getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fullPath = uploadPath + File.separator + avatarFileName;
        avatarImage.write(fullPath);

        String avatarDbPath = "uploads/" + avatarFileName;

        Food food = new Food(id, name, price, category, avatarDbPath);
        foodDAO.addFood(food);

        response.sendRedirect(request.getContextPath() + "/admin/foods");
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) return "";
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex >= 0) ? fileName.substring(dotIndex) : "";
    }
}
