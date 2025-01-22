package adminpack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");
        String bookingDate = request.getParameter("bookingDate");
        String customerName = request.getParameter("customerName");
        String tableNo = request.getParameter("tableNo");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            String query = "UPDATE BOOKING SET BOOKING_DATE = ?, CUSTOMER_NAME = ?, TABLE_NO = ? WHERE BOOKING_ID = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, bookingDate);
            ps.setString(2, customerName);
            ps.setString(3, tableNo);
            ps.setString(4, bookingId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("/Dynamic/BookingDetails.jsp");
    }
}
