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
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("toastMessage", "Session expired. Please try again.");
            newSession.setAttribute("toastType", "error");
            response.sendRedirect(request.getContextPath() + "/Dynamic/signup.jsp");
            return;
        }

        Integer sessionOtp = (Integer) session.getAttribute("otp");
        String userOtpStr = request.getParameter("otp");

        if (sessionOtp == null) {
            session.setAttribute("toastMessage", "Session expired. Please try again.");
            session.setAttribute("toastType", "error");
            response.sendRedirect(request.getContextPath() + "/Dynamic/signup.jsp");
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

                    session.setAttribute("toastMessage", "Registration successful!");
                    session.setAttribute("toastType", "success");
                    response.sendRedirect(request.getContextPath() + "/Dynamic/Home.jsp");
                } else {
                    session.setAttribute("toastMessage", "Email already exists. Try another email.");
                    session.setAttribute("toastType", "error");
                    response.sendRedirect(request.getContextPath() + "/Dynamic/signup.jsp");
                }
            } else {
                session.setAttribute("toastMessage", "Invalid OTP. Please try again.");
                session.setAttribute("toastType", "error");
                response.sendRedirect(request.getContextPath() + "/Dynamic/otpVerification.jsp");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("toastMessage", "Invalid OTP format.");
            session.setAttribute("toastType", "error");
            response.sendRedirect(request.getContextPath() + "/Dynamic/otpVerification.jsp");
        }
    }
}
