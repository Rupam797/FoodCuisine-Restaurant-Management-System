package com.rms.controller.user;

import com.rms.dao.UserDAO;
import com.rms.model.User;
import com.rms.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

/**
 * Controller: Handles forgot password flow (sends OTP).
 * Replaces: webpack.ForgotPassword
 */
public class ForgotPasswordServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("Useremail");

        User user = userDAO.findByEmail(email);

        if (user != null) {
            // Generate OTP
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);

            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            System.out.println("DEBUG: Generated OTP for " + email + " is: " + otp);

            // Send OTP email
            boolean sent = EmailUtil.sendOTP(email, otp);

            if (sent) {
                request.getRequestDispatcher("/Dynamic/VerifyOTP.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Error sending OTP. Please try again.");
                request.getRequestDispatcher("/Dynamic/ForgotPassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Email not found. Please enter a registered email.");
            request.getRequestDispatcher("/Dynamic/ForgotPassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
