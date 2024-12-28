package webpack;

import java.io.IOException;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;

public class VerifyOtp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false); // Avoid creating a new session

        if (session == null) {
            response.getWriter().println("<script>alert('Session expired. Please try again.'); window.location='/Dynamic/otpVerification.jsp';</script>");
            return;
        }

        // Retrieve the OTP and User Details from the session
        Integer sessionOtp = (Integer) session.getAttribute("otp"); // Stored OTP
        String userOtpStr = request.getParameter("otp"); // User input OTP

        if (sessionOtp == null) {
            response.getWriter().println("<script>alert('Session expired. Please try again.'); window.location='/Dynamic/otpVerification.jsp';</script>");
            return;
        }

        try {
            // Parse the user input to an Integer for comparison
            int userOtp = Integer.parseInt(userOtpStr);

            // Compare the OTP
            if (userOtp == sessionOtp) {
                // OTP matches - Complete the signup process
                String[] userDetails = (String[]) session.getAttribute("tempUser");
                String name = userDetails[0];
                String email = userDetails[1];
                String age = userDetails[2];
                String gender = userDetails[3];
                String password = userDetails[4];

                // Insert user into the database
                try {
                    // Registering the Oracle Driver
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

                    // Establishing the Connection
                    OracleConnection oconn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

                    String sql = "INSERT INTO USERS (NAME, EMAIL, AGE, GENDER, PASSWORD) VALUES (?, ?, ?, ?, ?)";
                    OraclePreparedStatement ops = (OraclePreparedStatement) oconn.prepareStatement(sql);

                    ops.setString(1, name);
                    ops.setString(2, email);
                    ops.setString(3, age);
                    ops.setString(4, gender);
                    ops.setString(5, password);

                    int rowsInserted = ops.executeUpdate();

                    if (rowsInserted > 0) {
                        // Set session attributes for user details
                        session.setAttribute("username", name);   // Store the user's name in session
                        session.setAttribute("userEmail", email); // Store the user's email in session
                        session.setAttribute("userAge", age);     // Store the user's age in session
                        session.setAttribute("userGender", gender); // Store the user's gender in session

                        // Cleanup temporary session attributes
                        session.removeAttribute("otp");
                        session.removeAttribute("tempUser");

                        // Redirect to Home Page
                        response.getWriter().println("<script>alert('Registration successful!'); window.location='/Dynamic/Home.jsp';</script>");
                    }

                    // Close resources
                    ops.close();
                    oconn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("<script>alert('Database error: Email already exist try with another email" + e.getMessage() + "'); window.location='/Dynamic/signup.jsp';</script>");
                }
            } else {
                // OTP does not match
                response.getWriter().println("<script>alert('Invalid OTP. Please try again.'); window.location='/Dynamic/otpVerification.jsp';</script>");
            }
        } catch (NumberFormatException e) {
            // Handle invalid input
            response.getWriter().println("<script>alert('Invalid OTP format. Please enter a valid number.'); window.location='/Dynamic/otpVerification.jsp';</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
