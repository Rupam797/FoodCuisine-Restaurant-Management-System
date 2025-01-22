package webpack;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class ForgotPassword extends HttpServlet {

    private OracleConnection oconn;
    private OraclePreparedStatement ost;
    private OracleResultSet ors = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("Useremail");
        HttpSession session = request.getSession();

        try {
            // Registering the Oracle Driver
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // Establishing the Connection
            oconn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            if (oconn == null) {
                out.println("Failed to establish a database connection.");
            }

            // Prepare SQL Query to verify user email
            ost = (OraclePreparedStatement) oconn.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
            ost.setString(1, email);
            ors = (OracleResultSet) ost.executeQuery();

            if (ors.next()) {
                // Generate OTP
                Random random = new Random();
                int otp = 100000 + random.nextInt(900000);
                session.setAttribute("otp", otp);
                session.setAttribute("email", email);

                // Send OTP to user email
                String host = "smtp.gmail.com";
                String user = "cusinefood0@gmail.com";
                String pass = "mzytfwiwuoowfuqp";
                String to = email;
                String subject = "Your OTP for Password Reset";
                String messageText = "Your OTP for password reset is: " + otp;
                boolean sessionDebug = false;

                Properties props = System.getProperties();
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");

                Session mailSession = Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(user));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setText(messageText);
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(host, user, pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();

                // Forward to OTP verification page
                request.getRequestDispatcher("/Dynamic/VerifyOTP.jsp").forward(request, response);

            } else {
                request.setAttribute("errorMessage", "Email not found. Please enter a registered email.");
                request.getRequestDispatcher("/Dynamic/ForgotPassword.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, "Database error", ex);
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2 style='color:red'>Database Error: " + ex.getMessage() + "</h2>");
            }
        } catch (Exception ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, "Error sending email", ex);
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2 style='color:red'>Error sending OTP: " + ex.getMessage() + "</h2>");
            }
        } finally {
            try {
                if (ors != null) ors.close();
                if (ost != null) ost.close();
                if (oconn != null) oconn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, "Error closing database resources", ex);
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
        return "Handles forgot password functionality with OTP verification";
    }
}