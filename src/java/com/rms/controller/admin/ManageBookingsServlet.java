package com.rms.controller.admin;

import com.rms.dao.BookingDAO;
import com.rms.model.Booking;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller: Admin booking management (list, edit, delete).
 * Replaces: adminpack.EditBooking, adminpack.DeleteBookingServlet, and DB code in BookingDetails.jsp
 */
public class ManageBookingsServlet extends HttpServlet {

    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookingIdParam = request.getParameter("bookingId");
        if (bookingIdParam != null) {
            try {
                int bookingId = Integer.parseInt(bookingIdParam);
                Booking booking = bookingDAO.getBookingById(bookingId);
                request.setAttribute("booking", booking);
                request.getRequestDispatcher("/Dynamic/EditBooking.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                // ignore and fall back to list
            }
        }

        List<Booking> bookings = bookingDAO.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/Dynamic/BookingDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            bookingDAO.deleteBooking(bookingId);
        } else if ("edit".equals(action)) {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            String bookingDate = request.getParameter("bookingDate");
            String customerName = request.getParameter("customerName");
            String tableNo = request.getParameter("tableNo");
            bookingDAO.updateBooking(bookingId, bookingDate, customerName, tableNo);
        }

        response.sendRedirect(request.getContextPath() + "/admin/bookings");
    }
}
