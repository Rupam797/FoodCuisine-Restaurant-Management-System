package com.rms.dao;

import com.rms.model.Booking;
import com.rms.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Booking operations.
 */
public class BookingDAO {

    private static final Logger LOGGER = Logger.getLogger(BookingDAO.class.getName());

    /**
     * Inserts a new booking.
     */
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO booking (first_name, last_name, email, table_type, phone_no, placement, booking_date, start_time, end_time, note) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, booking.getFirstName());
            pst.setString(2, booking.getLastName());
            pst.setString(3, booking.getEmail());
            pst.setString(4, booking.getTableType());
            pst.setString(5, booking.getPhoneNo());
            pst.setString(6, booking.getPlacement());
            pst.setString(7, booking.getBookingDate()); // MySQL handles YYYY-MM-DD natively
            pst.setString(8, booking.getStartTime());
            pst.setString(9, booking.getEndTime());
            pst.setString(10, booking.getNote());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding booking", e);
        }
        return false;
    }

    /**
     * Gets all bookings (for admin panel).
     */
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM booking ORDER BY booking_date";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all bookings", e);
        }
        return bookings;
    }

    /**
     * Updates a booking.
     */
    public boolean updateBooking(int bookingId, String bookingDate, String customerName, String tableNo) {
        String sql = "UPDATE booking SET booking_date = ?, first_name = ?, table_type = ? WHERE booking_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, bookingDate);
            pst.setString(2, customerName);
            pst.setString(3, tableNo);
            pst.setInt(4, bookingId);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating booking: " + bookingId, e);
        }
        return false;
    }

    /**
     * Deletes a booking by ID.
     */
    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM booking WHERE booking_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, bookingId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting booking: " + bookingId, e);
        }
        return false;
    }

    /**
     * Gets a booking by ID.
     */
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM booking WHERE booking_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, bookingId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBooking(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting booking by ID: " + bookingId, e);
        }
        return null;
    }

    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        Booking b = new Booking();
        b.setBookingId(rs.getInt("booking_id"));
        b.setFirstName(rs.getString("first_name"));
        b.setLastName(rs.getString("last_name"));
        b.setEmail(rs.getString("email"));
        b.setTableType(rs.getString("table_type"));
        b.setPhoneNo(rs.getString("phone_no"));
        b.setPlacement(rs.getString("placement"));
        Date date = rs.getDate("booking_date");
        b.setBookingDate(date != null ? date.toString() : "");
        b.setStartTime(rs.getString("start_time"));
        b.setEndTime(rs.getString("end_time"));
        b.setNote(rs.getString("note"));
        return b;
    }
}
