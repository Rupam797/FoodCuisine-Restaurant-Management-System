package webpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class LoginUser extends HttpServlet {

    private OracleConnection oconn;
    private OraclePreparedStatement ost;
    private OracleResultSet ors = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("Useremail");
        String password = request.getParameter("Userpassword");

        try {
            // Registering the Oracle Driver
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // Establishing the Connection
            oconn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            if (oconn == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            // Prepare SQL Query to verify user credentials
            ost = (OraclePreparedStatement) oconn.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?");
            ost.setString(1, email);
            ost.setString(2, password);
            ors = (OracleResultSet) ost.executeQuery();

            if (ors.next()) {
                // Get user details from the result set
                String username = ors.getString("NAME");
                String userEmail = ors.getString("EMAIL");
                String userAge = ors.getString("AGE");
                String userGender = ors.getString("GENDER");

                // Create a new session and store user details
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("userEmail", userEmail);
                session.setAttribute("userAge", userAge);
                session.setAttribute("userGender", userGender);

                // Set success message to display on Home.jsp
                request.setAttribute("loginMessage", "Login successful!");

                // Forward to Home.jsp to display the success message
                request.getRequestDispatcher("/Dynamic/Home.jsp").forward(request, response);

            } else if ("admin@gmail.com".equals(email) && "Admin@123".equals(password)) {
                // Admin login check
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email); // Admin email stored in session

                // Redirect to Admin Dashboard
                response.sendRedirect("/Dynamic/ADashbord.jsp");

            } else {
                // Invalid login credentials
                request.setAttribute("loginMessage", "Invalid email or password. Please try again.");
                request.getRequestDispatcher("/Dynamic/login.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginUser.class.getName()).log(Level.SEVERE, "Database error", ex);
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2 style='color:red'>Database Error: " + ex.getMessage() + "</h2>");
            }
        } finally {
            try {
                if (ors != null) ors.close();
                if (ost != null) ost.close();
                if (oconn != null) oconn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginUser.class.getName()).log(Level.SEVERE, "Error closing database resources", ex);
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
        return "Handles user login with session management";
    }
}
