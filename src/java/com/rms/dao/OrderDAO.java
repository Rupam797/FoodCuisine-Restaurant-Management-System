package com.rms.dao;

import com.rms.model.Order;
import com.rms.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Order operations.
 */
public class OrderDAO {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    /**
     * Inserts a new order.
     */
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO order_details (customer_name, phone_no, food_details, total_amount, payment_status, order_date) "
                   + "VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, order.getCustomerName());
            pst.setString(2, order.getPhoneNo());
            pst.setString(3, order.getFoodDetails());
            pst.setInt(4, order.getTotalAmount());
            pst.setString(5, order.getPaymentStatus());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding order", e);
        }
        return false;
    }

    /**
     * Gets all orders (for admin panel).
     */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM order_details ORDER BY order_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setPhoneNo(rs.getString("phone_no"));
                order.setFoodDetails(rs.getString("food_details"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                Timestamp ts = rs.getTimestamp("order_date");
                order.setOrderDate(ts != null ? ts.toString() : "");
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all orders", e);
        }
        return orders;
    }
}
