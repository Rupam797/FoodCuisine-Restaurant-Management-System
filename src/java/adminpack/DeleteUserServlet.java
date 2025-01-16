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


public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve email from request parameter
        String email = request.getParameter("email");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Connect to Oracle database
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            // Delete query using email
            String query = "DELETE FROM USERS WHERE EMAIL = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);

            // Execute delete
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

        // Redirect back to user details page
        response.sendRedirect("/Dynamic/UserDetails.jsp");
    }
}
