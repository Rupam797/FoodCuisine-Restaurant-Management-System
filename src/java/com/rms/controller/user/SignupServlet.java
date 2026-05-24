package com.rms.controller.user;

import com.rms.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

/**
 * Controller: Handles user signup (generates OTP and sends email).
 * Replaces: webpack.SignupUser
 */
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("Uname");
        String email = request.getParameter("Uemail");
        String age = request.getParameter("Uage");
        String gender = request.getParameter("Ugender");
        String password = request.getParameter("Upassword");

        // Generate 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        // Send OTP email
        boolean emailSent = EmailUtil.sendOTP(email, otp);

        if (emailSent) {
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("tempUser", new String[]{name, email, age, gender, password});

            response.sendRedirect(request.getContextPath() + "/Dynamic/otpVerification.jsp");
        } else {
            request.setAttribute("errorMessage", "Error sending OTP. Please try again.");
            request.getRequestDispatcher("/Dynamic/signup.jsp").forward(request, response);
        }
    }
}
