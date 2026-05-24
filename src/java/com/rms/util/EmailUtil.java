package com.rms.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Centralized email utility class.
 * All email sending logic is consolidated here.
 * Reads SMTP config from db.properties via DatabaseUtil.
 */
public class EmailUtil {

    private static final Logger LOGGER = Logger.getLogger(EmailUtil.class.getName());

    /**
     * Creates and returns a configured JavaMail Session.
     */
    private static Session getMailSession() {
        final String host = DatabaseUtil.getProperty("email.host");
        final String port = DatabaseUtil.getProperty("email.port");
        final String username = DatabaseUtil.getProperty("email.username");
        final String password = DatabaseUtil.getProperty("email.password");

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * Sends a plain-text email.
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param body    email body (plain text)
     * @return true if sent successfully, false otherwise
     */
    public static boolean sendEmail(String to, String subject, String body) {
        try {
            Session session = getMailSession();
            String from = DatabaseUtil.getProperty("email.username");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            LOGGER.info("Email sent to: " + to);
            return true;
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Failed to send email to: " + to, e);
            return false;
        }
    }

    /**
     * Sends an HTML email.
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param html    email body (HTML content)
     * @return true if sent successfully, false otherwise
     */
    public static boolean sendHtmlEmail(String to, String subject, String html) {
        try {
            Session session = getMailSession();
            String from = DatabaseUtil.getProperty("email.username");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(html, "text/html");

            Transport.send(message);
            LOGGER.info("HTML email sent to: " + to);
            return true;
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Failed to send HTML email to: " + to, e);
            return false;
        }
    }

    /**
     * Sends an OTP email for signup or password reset.
     */
    public static boolean sendOTP(String to, int otp) {
        String subject = "Your OTP for Food Cuisine";
        String body = "WELCOME TO FOOD CUISINE\n\nYour OTP is: " + otp + "\n\nThis OTP is valid for 10 minutes.";
        return sendEmail(to, subject, body);
    }

    /**
     * Sends a booking confirmation email with HTML formatting.
     */
    public static boolean sendBookingConfirmation(String to, String firstName, String lastName,
            String tableType, String placement, String date, String startTime,
            String endTime, String guestNumber, String note) {
        String subject = "Booking Confirmation - Food Cuisine";
        String html = "<h2>Booking Confirmation</h2>"
                + "<p>Dear " + firstName + " " + lastName + ",</p>"
                + "<p>Your table has been successfully booked at our restaurant. Here are the details:</p>"
                + "<ul>"
                + "<li>Table Type: " + tableType + "</li>"
                + "<li>Placement: " + placement + "</li>"
                + "<li>Booking Date: " + date + "</li>"
                + "<li>Starting Time: " + startTime + "</li>"
                + "<li>Ending Time: " + endTime + "</li>"
                + "<li>Number of Guests: " + guestNumber + "</li>"
                + "<li>Special Note: " + (note == null || note.isEmpty() ? "None" : note) + "</li>"
                + "</ul>"
                + "<p>We look forward to serving you!</p>";
        return sendHtmlEmail(to, subject, html);
    }

    /**
     * Sends an order confirmation email.
     */
    public static boolean sendOrderConfirmation(String customerName, String phoneNumber,
            String foodDetails, int totalAmount) {
        String to = DatabaseUtil.getProperty("email.username"); // sends to restaurant
        String subject = "Order Confirmation - Your Recent Purchase";
        String body = "Dear " + customerName + ",\n\n"
                + "Thank you for your order! Here are the details:\n\n"
                + "Customer Name: " + customerName + "\n"
                + "Phone Number: " + phoneNumber + "\n\n"
                + "Ordered Items: " + foodDetails + "\n"
                + "Total Amount: ₹" + totalAmount + "\n\n"
                + "Payment Status: Paid\n\n"
                + "Thank you for choosing us!\n\n"
                + "Best Regards,\nFood Cuisine Team";
        return sendEmail(to, subject, body);
    }

    /**
     * Sends a feedback email to the restaurant.
     */
    public static boolean sendFeedback(String firstName, String lastName, String email,
            String subject, String message) {
        String to = DatabaseUtil.getProperty("email.username");
        String emailSubject = "Feedback from " + firstName + " " + lastName;
        String body = "You have received a new feedback.\n\n"
                + "Name: " + firstName + " " + lastName + "\n"
                + "Email: " + email + "\n"
                + "Subject: " + subject + "\n"
                + "Message:\n" + message;
        return sendEmail(to, emailSubject, body);
    }

    /**
     * Sends a complaint email to the restaurant.
     */
    public static boolean sendComplaint(String fullName, String phoneNo, String email,
            String subject, String message) {
        String to = DatabaseUtil.getProperty("email.username");
        String emailSubject = "Complaint from " + fullName;
        String body = "You have received a new complaint.\n\n"
                + "Name: " + fullName + "\n"
                + "Phone: " + phoneNo + "\n"
                + "Email: " + email + "\n"
                + "Subject: " + subject + "\n"
                + "Message:\n" + message;
        return sendEmail(to, emailSubject, body);
    }
}
