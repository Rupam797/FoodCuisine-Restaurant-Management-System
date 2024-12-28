package bookpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TableBooking extends HttpServlet {

    String fname, lname, email, table, ph, place, date, startTime, endTime, note;

    // Declaring Oracle objects
    Connection oconn;
    PreparedStatement ops;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Retrieve form data from request
            fname = request.getParameter("FirstName");
            lname = request.getParameter("LastName");
            email = request.getParameter("Email");
            table = request.getParameter("TableType");
            ph = request.getParameter("GuestNumber");
            place = request.getParameter("Placement");
            date = request.getParameter("Date");
            startTime = request.getParameter("StartTime");
            endTime = request.getParameter("EndTime");
            note = request.getParameter("Note");

           

            // Register Oracle Driver
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // Establishing the Connection
            oconn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            if (oconn != null) {
                System.out.println("Connection established successfully!");
            }

            // Prepare SQL Query with TO_DATE for date, start time, and end time
            String sql = "INSERT INTO BOOKING (FIRST_NAME, LAST_NAME, EMAIL, TABLE_TYPE, PHONE_NO, PLACEMENT, BOOKING_DATE, START_TIME, END_TIME, NOTE) " +
                         "VALUES (?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
            ops = oconn.prepareStatement(sql);

            // Set Query Parameters
            ops.setString(1, fname);
            ops.setString(2, lname);
            ops.setString(3, email);
            ops.setString(4, table);
            ops.setString(5, ph);
            ops.setString(6, place);
            ops.setString(7, date);  // Ensure this is in 'YYYY-MM-DD' format
            ops.setString(8, startTime);  // Starting time
            ops.setString(9, endTime);    // Ending time
            ops.setString(10, note);

            // Execute the Query
            int rowsInserted = ops.executeUpdate();

            if (rowsInserted > 0) {
                // Send email confirmation
                sendEmailConfirmation(email, fname, lname, table, place, date, startTime, endTime, note);

                // Generate success alert and redirect to home page
                out.println("<html>");
                out.println("<head>");
                out.println("<script type='text/javascript'>");
                out.println("alert('Your table has been booked successfully!');");
                out.println("window.location = '/Dynamic/Home.jsp';"); // Redirect to the home page after the alert
                out.println("</script>");
                out.println("</head>");
                out.println("</html>");
            } else {
                // Redirect back to signup page if insertion fails
                response.sendRedirect("/Dynamic/signup.jsp");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2 style='color:red'>Database Error: " + ex.getMessage() + "</h2>");
            }
        } finally {
            // Close Resources
            try {
                if (ops != null) ops.close();
                if (oconn != null) oconn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendEmailConfirmation(String recipientEmail, String fname, String lname, String table, String place, String date, String startTime, String endTime, String note) {
        // SMTP server configuration
        String host = "smtp.gmail.com";  // For Gmail
        final String user = "cusinefood0@gmail.com";  // Your email address
        final String password = "mzytfwiwuoowfuqp";  // Your email password

        // Set properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(user));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Booking Confirmation for Table");

            // Set the actual message
            String content = "<h2>Booking Confirmation</h2>" +
                             "<p>Dear " + fname + " " + lname + ",</p>" +
                             "<p>Your table has been successfully booked at our restaurant. Here are the details:</p>" +
                             "<ul>" +
                             "<li>Table Type: " + table + "</li>" +
                             "<li>Placement: " + place + "</li>" +
                             "<li>Booking Date: " + date + "</li>" +
                             "<li>Starting Time: " + startTime + "</li>" +
                             "<li>Ending Time: " + endTime + "</li>" +
                             "<li>Number of Guests: " + ph + "</li>" +
                             "<li>Special Note: " + (note.isEmpty() ? "None" : note) + "</li>" +
                             "</ul>" +
                             "<p>We look forward to serving you!</p>";

            message.setContent(content, "text/html");

            // Send the email
            Transport.send(message);

            System.out.println("Booking confirmation email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
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
        return "TableBooking Servlet to handle table booking form submissions.";
    }
}
