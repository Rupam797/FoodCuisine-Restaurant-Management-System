package cartpack;

import cartpack.Cart;
import cartpack.CartItem;


import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class PaymentServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // Update with your database URL
    private static final String DB_USER = "RUPAM"; // Update with your Oracle DB username
    private static final String DB_PASSWORD = "GIRI"; // Update with your Oracle DB password

    // Email configuration
    private static final String SMTP_HOST = "smtp.your-email-provider.com"; // Change to your SMTP server
    private static final String SMTP_PORT = "587"; // SMTP port (use 465 for SSL)
    private static final String EMAIL_USERNAME = "cusinefood0@gmail.com"; // Your email
    private static final String EMAIL_PASSWORD = "mzytfwiwuoowfuqp"; // Your email password

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            // Get session and retrieve the cart
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            

            if (cart == null || cart.getItems().isEmpty()) {
                out.println("<h3>Your cart is empty!</h3>");
                return;
            }

            // Retrieve form parameters
            String customerName = request.getParameter("customer-name");
            String phoneNumber = request.getParameter("phone-number");
            String paymentStatus = "Paid"; // Assuming payment is successful
            int totalAmount = cart.getTotal();

            // Create a single string for food details
            List<CartItem> items = cart.getItems();
            StringBuilder foodDetails = new StringBuilder();
            for (CartItem item : items) {
                foodDetails.append("ID: ").append(item.getId())
                           .append(", Name: ").append(item.getName())
                           .append(", Quantity: ").append(item.getQuantity())
                           .append(", Price: ").append(item.getPrice())
                           .append("; ");
            }

            // Store order details in session
            session.setAttribute("customerName", customerName);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("totalAmount", totalAmount);
            session.setAttribute("paymentStatus", paymentStatus);
            session.setAttribute("foodDetails", foodDetails.toString());

            // Database connection and insertion
            String insertOrderSQL = "INSERT INTO ORDER_DETAILS (CUSTOMER_NAME, PHONE_NO, FOOD_DETAILS, TOTAL_AMOUNT, PAYMENT_STATUS, SYS_DATE) " +
                                    "VALUES (?, ?, ?, ?, ?, SYSDATE)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = conn.prepareStatement(insertOrderSQL)) {

                // Set parameters for the prepared statement
                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, phoneNumber);
                preparedStatement.setString(3, foodDetails.toString());
                preparedStatement.setInt(4, totalAmount);
                preparedStatement.setString(5, paymentStatus);

                // Execute the statement
                preparedStatement.executeUpdate();

                // Send confirmation email to the customer
                sendEmail(customerName, phoneNumber, foodDetails.toString(), totalAmount);

                // Clear the cart after successful payment
                session.removeAttribute("cart");

                // Redirect to confirmation page
                response.sendRedirect("/Dynamic/confirmation.jsp");
            } catch (SQLException e) {
                out.println("<h3>Error processing your payment: " + e.getMessage() + "</h3>");
            }
        }
    }

    // Method to send an email
    private void sendEmail(String customerName, String phoneNumber, String foodDetails, int totalAmount) {
        String to = "cusinefood0@gmail.com"; // Retrieve the user's email address from the session or form
        
        String subject = "Order Confirmation - Your Recent Purchase";
        String body = "Dear " + customerName + ",\n\n"
                    + "Thank you for your order! Here are the details:\n\n"
                    + "Customer Name: " + customerName + "\n"
                    + "Phone Number: " + phoneNumber + "\n\n"
                    + "Ordered Items: " + foodDetails + "\n"
                    + "Total Amount: " + totalAmount + "\n\n"
                    + "Payment Status: Paid\n\n"
                    + "Thank you for choosing us!\n\n"
                    + "Best Regards,\n"
                    + "Your Restaurant Team";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
