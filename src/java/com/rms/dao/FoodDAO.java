package com.rms.dao;

import com.rms.model.Food;
import com.rms.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Food operations.
 */
public class FoodDAO {

    private static final Logger LOGGER = Logger.getLogger(FoodDAO.class.getName());

    /**
     * Adds a new food item to the database.
     */
    public boolean addFood(Food food) {
        String sql = "INSERT INTO foods (food_name, food_price, food_category, food_img, food_desc) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, food.getFoodName());
            pst.setInt(2, food.getFoodPrice());
            pst.setString(3, food.getFoodCategory());
            pst.setString(4, food.getFoodImg());
            pst.setString(5, food.getFoodDesc());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding food", e);
        }
        return false;
    }

    /**
     * Gets foods by category (e.g., "Top Dishes", "Breakfast", "Lunch", "Dinner").
     */
    public List<Food> getFoodsByCategory(String category) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT food_id, food_name, food_price, food_img, food_desc FROM foods WHERE food_category = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, category);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setFoodPrice(rs.getInt("food_price"));
                food.setFoodImg(rs.getString("food_img"));
                food.setFoodCategory(category);
                food.setFoodDesc(rs.getString("food_desc"));
                foods.add(food);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting foods by category: " + category, e);
        }
        return foods;
    }

    /**
     * Gets a single food item by ID (for add-to-cart).
     */
    public Food getFoodById(int foodId) {
        String sql = "SELECT food_id, food_name, food_price, food_img, food_category, food_desc FROM foods WHERE food_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, foodId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Food(
                    rs.getInt("food_id"),
                    rs.getString("food_name"),
                    rs.getInt("food_price"),
                    rs.getString("food_category"),
                    rs.getString("food_img"),
                    rs.getString("food_desc")
                );
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting food by ID: " + foodId, e);
        }
        return null;
    }

    /**
     * Gets all food items (for admin panel).
     */
    public List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods ORDER BY food_id ASC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                foods.add(new Food(
                    rs.getInt("food_id"),
                    rs.getString("food_name"),
                    rs.getInt("food_price"),
                    rs.getString("food_category"),
                    rs.getString("food_img"),
                    rs.getString("food_desc")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all foods", e);
        }
        return foods;
    }

    /**
     * Deletes a food item by ID.
     */
    public boolean deleteFood(int foodId) {
        String sql = "DELETE FROM foods WHERE food_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, foodId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting food: " + foodId, e);
        }
        return false;
    }
}
