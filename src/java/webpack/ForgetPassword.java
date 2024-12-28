package webpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@WebServlet(name = "ForgotPassword", urlPatterns = {"/ForgotPassword"})
public class ForgetPassword extends HttpServlet {

    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final String DB_USER = "RUPAM";
    private final String DB_PASS = "GIRI";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("Uemail");

        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("oracle.jdbc.OracleDriver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                
                // Verify if email exists in the database
                String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Email exists, generate OTP
                    String otp = generateOtp(6);
                    // Store OTP in session or database for verification later
                    request.getSession().setAttribute("otp", otp);
                    request.getSession().setAttribute("email", email);
                    
                    // Send OTP via email
                    sendOtpEmail(email, otp);

                    out.println("<h3>OTP has been sent to your email.</h3>");
                } else {
                    // Email does not exist
                    out.println("<h3 style='color:red'>Email not found in our database.</h3>");
                }

                rs.close();
                pstmt.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<h3 style='color:red'>Database error: " + e.getMessage() + "</h3>");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateOtp(int length) {
        // Generates a numeric OTP with the given length
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return otp.toString();
    }

    private void sendOtpEmail(String toEmail, String otp) {
        // SMTP configuration
        final String fromEmail = "cusinefood0@gmail.com";
        final String password = "Amit@rupsha123";
        String subject = "Your OTP Code";
        String message = "Dear user,\n\nYour OTP code for password reset is: " + otp + "\n\nBest regards,\nFood Cuisine Support";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.example.com"); // Set your SMTP host
        props.put("mail.smtp.port", "587"); // Set the SMTP port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);

            System.out.println("OTP sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles Forgot Password by verifying email and sending OTP";
    }
}

