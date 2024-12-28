package feedbackpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Complain extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fullName = request.getParameter("Fname");
        String phoneNo = request.getParameter("phno");
        String email = request.getParameter("Email");
        String subject = request.getParameter("Subject");
        String message = request.getParameter("Message");

        // SMTP configuration
        String host = "smtp.gmail.com"; // Change if using another SMTP provider
        final String username = "cusinefood0@gmail.com"; // Replace with your SMTP email
        final String password = "mzytfwiwuoowfuqp"; // Replace with your SMTP email password or app-specific password

        // Setting up SMTP properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // Use 465 for SSL

        // Get the Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("cusinefood0@gmail.com")); // Replace with recipient's email
            msg.setSubject("Complain from " + fullName);
            msg.setText("You have received a new complain.\n\n"
                    + "Name: " + fullName + "\n"
                    + "Name: " + phoneNo + "\n"
                    + "Email: " + email + "\n"
                    + "Subject: " + subject + "\n"
                    + "Message:\n" + message);

            // Send the message
            Transport.send(msg);

            try (PrintWriter out = response.getWriter()) {
                response.sendRedirect("/Dynamic/Home.jsp");
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
        return "Feedback servlet that processes feedback submissions and sends them via email";
    }
}

