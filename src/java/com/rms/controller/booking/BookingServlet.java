package com.rms.controller.booking;

import com.rms.dao.BookingDAO;
import com.rms.model.Booking;
import com.rms.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Handles table booking submission.
 * Replaces: bookpack.TableBooking
 */
public class BookingServlet extends HttpServlet {

    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("Email");
        String tableType = request.getParameter("TableType");
        String guestNumber = request.getParameter("GuestNumber");
        String placement = request.getParameter("Placement");
        String date = request.getParameter("Date");
        String startTime = request.getParameter("StartTime");
        String endTime = request.getParameter("EndTime");
        String note = request.getParameter("Note");

        Booking booking = new Booking(firstName, lastName, email, tableType,
                guestNumber, placement, date, startTime, endTime, note);

        boolean inserted = bookingDAO.addBooking(booking);

        if (inserted) {
            // Send booking confirmation email
            EmailUtil.sendBookingConfirmation(email, firstName, lastName, tableType,
                    placement, date, startTime, endTime, guestNumber, note);

            // Show success toast and redirect
            HttpSession session = request.getSession();
            session.setAttribute("toastMessage", "Your table has been booked successfully!");
            session.setAttribute("toastType", "success");
            response.sendRedirect(request.getContextPath() + "/Dynamic/Home.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("toastMessage", "Failed to book table. Please try again.");
            session.setAttribute("toastType", "error");
            response.sendRedirect(request.getContextPath() + "/Dynamic/booking.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
