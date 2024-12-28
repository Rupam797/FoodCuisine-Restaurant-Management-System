package webpack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FetchEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the email address from the request parameter
        String email = request.getParameter("email");

        // Database connection parameters
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl"; // Update to your DB URL
        String dbUsername = "RUPAM";
        String dbPassword = "GIRI";

        // Validate input email
        if (email == null || email.trim().isEmpty()) {
            response.sendRedirect("error.jsp"); // Redirect to error page for invalid input
            return;
        }

        // Variable to store the email fetched from the database
        String fetchedEmail = null;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            // Registering the Oracle Driver
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            // SQL query to fetch the email from the database
            String query = "SELECT EMAIL FROM USERS WHERE EMAIL = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email.trim()); // Set the provided email
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    fetchedEmail = rs.getString("email"); // Get the email from the result set
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database connection error.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // If the email is found in the database
        if (fetchedEmail != null) {
            // Generate OTP
            String otp = generateOTP();

            // Attempt to send OTP via email
            boolean emailSent = sendEmail(fetchedEmail, otp);

            if (emailSent) {
                // Store OTP in session for verification
                HttpSession session = request.getSession();
                session.setAttribute("otp", otp);
                session.setAttribute("email", fetchedEmail);

                // Redirect to the OTP verification page
                response.sendRedirect("/Dynamic/EnterOTP.jsp");
            } else {
                // Redirect to error page if email sending fails
                request.setAttribute("errorMessage", "Failed to send OTP. Please try again later.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // If the email is not found in the database, redirect to an error page
            request.setAttribute("errorMessage", "Email not found in the system.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Method to generate a random OTP
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate 6-digit OTP
        return String.valueOf(otp);
    }

    // Method to send OTP via email
    private boolean sendEmail(String email, String otp) {
        try {
            // Placeholder for sending email logic
            // Use JavaMail API or a similar library to send the email

            // Example:
             Properties props = new Properties();
             props.put("mail.smtp.host", "smtp.example.com");
             props.put("mail.smtp.port", "587");
             props.put("mail.smtp.auth", "true");
             Session session = Session.getInstance(props, new Authenticator() {
                 protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication("your_email", "your_password");
                 }
             });
             MimeMessage message = new MimeMessage(session);
             message.setFrom(new InternetAddress("your_email"));
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
             message.setSubject("Your OTP Code");
             message.setText("Your OTP is: " + otp);
             Transport.send(message);

            // Simulate email sending success
            System.out.println("Email sent to " + email + " with OTP: " + otp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
