package webpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;

public class SignupUser extends HttpServlet {

    String name, email, age, gender, password;
    OracleConnection oconn;
    OraclePreparedStatement ops;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            name = request.getParameter("Uname");
            email = request.getParameter("Uemail");
            age = request.getParameter("Uage");
            gender = request.getParameter("Ugender");
            password = request.getParameter("Upassword");

            // Generate a random OTP
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000); // 6-digit OTP

            // Send OTP to user's email
            boolean emailSent = sendEmail(email, otp);
            if (emailSent) {
                // Store OTP in session for verification
                HttpSession session = request.getSession();
                session.setAttribute("otp", otp);
                session.setAttribute("tempUser", new String[]{name, email, age, gender, password});

                // Redirect to OTP verification page
                response.sendRedirect("/Dynamic/otpVerification.jsp");
            } else {
                out.println("<h2 style='color:red'>Error sending OTP. Please try again.</h2>");
            }

        } catch (Exception e) {
            Logger.getLogger(SignupUser.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private boolean sendEmail(String recipientEmail, int otp) {
        final String senderEmail = "cusinefood0@gmail.com"; // Replace with your email
        final String senderPassword = "mzytfwiwuoowfuqp"; // Replace with your email password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP for Signup");
            message.setText("Your OTP is: " + otp);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            Logger.getLogger(SignupUser.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
