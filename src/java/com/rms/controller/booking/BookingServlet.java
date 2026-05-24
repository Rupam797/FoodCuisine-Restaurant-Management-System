package com.rms.controller.booking;

import com.rms.dao.BookingDAO;
import com.rms.model.Booking;
import com.rms.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

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

            // Show success alert and redirect
            PrintWriter out = response.getWriter();
            out.println("<html><head>");
            out.println("<script type='text/javascript'>");
            out.println("alert('Your table has been booked successfully!');");
            out.println("window.location = '" + request.getContextPath() + "/Dynamic/Home.jsp';");
            out.println("</script>");
            out.println("</head></html>");
        } else {
            response.sendRedirect(request.getContextPath() + "/Dynamic/booking.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
