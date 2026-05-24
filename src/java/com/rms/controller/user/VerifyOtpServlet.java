package com.rms.controller.user;

import com.rms.dao.UserDAO;
import com.rms.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Verifies OTP and completes user registration.
 * Replaces: webpack.VerifyOtpVerification
 */
public class VerifyOtpServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.getWriter().println("<script>alert('Session expired. Please try again.'); window.location='" + request.getContextPath() + "/Dynamic/signup.jsp';</script>");
            return;
        }

        Integer sessionOtp = (Integer) session.getAttribute("otp");
        String userOtpStr = request.getParameter("otp");

        if (sessionOtp == null) {
            response.getWriter().println("<script>alert('Session expired. Please try again.'); window.location='" + request.getContextPath() + "/Dynamic/signup.jsp';</script>");
            return;
        }

        try {
            int userOtp = Integer.parseInt(userOtpStr);

            if (userOtp == sessionOtp) {
                // OTP matches — insert user into DB
                String[] userDetails = (String[]) session.getAttribute("tempUser");

                User user = new User();
                user.setName(userDetails[0]);
                user.setEmail(userDetails[1]);
                user.setAge(Integer.parseInt(userDetails[2]));
                user.setGender(userDetails[3]);
                user.setPassword(userDetails[4]);
                user.setRole("user");

                boolean inserted = userDAO.insertUser(user);

                if (inserted) {
                    // Set session for logged-in user
                    session.setAttribute("username", user.getName());
                    session.setAttribute("userEmail", user.getEmail());
                    session.setAttribute("userAge", userDetails[2]);
                    session.setAttribute("userGender", user.getGender());
                    session.setAttribute("userRole", "user");

                    // Cleanup temp attributes
                    session.removeAttribute("otp");
                    session.removeAttribute("tempUser");

                    response.getWriter().println("<script>alert('Registration successful!'); window.location='" + request.getContextPath() + "/Dynamic/Home.jsp';</script>");
                } else {
                    response.getWriter().println("<script>alert('Database error: Email already exists. Try another email.'); window.location='" + request.getContextPath() + "/Dynamic/signup.jsp';</script>");
                }
            } else {
                response.getWriter().println("<script>alert('Invalid OTP. Please try again.'); window.location='" + request.getContextPath() + "/Dynamic/otpVerification.jsp';</script>");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("<script>alert('Invalid OTP format.'); window.location='" + request.getContextPath() + "/Dynamic/otpVerification.jsp';</script>");
        }
    }
}
