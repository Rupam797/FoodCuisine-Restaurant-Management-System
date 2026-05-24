package com.rms.controller.user;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Verifies OTP for password reset.
 * Replaces: webpack.OTPVerify
 */
public class VerifyResetOtpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int sessionOtp = (int) session.getAttribute("otp");
        String email = (String) session.getAttribute("email");

        try {
            int enteredOtp = Integer.parseInt(request.getParameter("otp"));

            if (enteredOtp == sessionOtp) {
                request.setAttribute("email", email);
                request.getRequestDispatcher("/Dynamic/ResetPassword.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid OTP. Please try again.");
                request.getRequestDispatcher("/Dynamic/VerifyOTP.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid OTP format. Please enter a valid OTP.");
            request.getRequestDispatcher("/Dynamic/VerifyOTP.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
