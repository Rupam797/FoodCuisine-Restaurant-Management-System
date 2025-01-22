package webpack;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResetPassword extends HttpServlet {

    private OracleConnection oconn;
    private OraclePreparedStatement ost;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String newPassword = request.getParameter("newPassword");

        // Input validation
        if (email == null || email.trim().isEmpty() || newPassword == null || newPassword.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email and Password cannot be empty.");
            request.getRequestDispatcher("/Dynamic/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Log email and new password for debugging
        System.out.println("Email from session: " + email);
        System.out.println("New Password: " + newPassword);

        try {
            // Registering the Oracle Driver
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // Establishing the Connection
            oconn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            if (oconn == null) {
                System.out.println("Failed to establish a database connection.");
            }

            // Update Password Query
            String sql = "UPDATE USERS SET PASSWORD = ? WHERE EMAIL = ?";
            ost = (OraclePreparedStatement) oconn.prepareStatement(sql);
            ost.setString(1, newPassword);
            ost.setString(2, email);

            int rowsUpdated = ost.executeUpdate();
            System.out.println("Rows Updated: " + rowsUpdated);

            if (rowsUpdated > 0) {
                // Invalidate session if needed
                session.invalidate();  // Optional, depending on app logic
                request.setAttribute("successMessage", "Password reset successfully.");
                request.getRequestDispatcher("/Dynamic/login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Error resetting password. Email not found.");
                request.getRequestDispatcher("/Dynamic/ResetPassword.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Database Error: " + ex.getMessage());
            request.getRequestDispatcher("/Dynamic/ResetPassword.jsp").forward(request, response);
        } finally {
            try {
                if (ost != null) ost.close();
                if (oconn != null) oconn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles password reset functionality.";
    }
}
