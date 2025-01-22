
package adminpack;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the booking ID from the request
        String bookingId = request.getParameter("bookingId");

        // JDBC variables
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // 1. Load the Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. Connect to the database
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            // 3. Create a SQL delete query
            String sql = "DELETE FROM BOOKING WHERE BOOKING_ID = ?";

            // 4. Prepare the statement
            stmt = conn.prepareStatement(sql);

            // 5. Set the bookingId parameter
            stmt.setString(1, bookingId);

            // 6. Execute the query
            int rowsAffected = stmt.executeUpdate();

            // 7. Redirect back to the JSP page to refresh the data
            if (rowsAffected > 0) {
                response.sendRedirect("/Dynamic/BookingDetails.jsp"); // assuming your JSP is called booking.jsp
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        } finally {
            // 8. Clean up resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
