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


public class EditUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Connect to Oracle database
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            // Update query using email
            String query = "UPDATE USERS SET NAME = ?, AGE = ?, GENDER = ? WHERE EMAIL = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(age));
            ps.setString(3, gender);
            ps.setString(4, email);

            // Execute update
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
        response.sendRedirect("/UserDetails.jsp");
    }
}
