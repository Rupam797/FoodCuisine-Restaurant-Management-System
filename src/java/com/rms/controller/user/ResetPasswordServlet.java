package com.rms.controller.user;

import com.rms.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Resets user password after OTP verification.
 * Replaces: webpack.ResetPassword
 */
public class ResetPasswordServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String newPassword = request.getParameter("newPassword");

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Session expired or invalid. Please start over.");
            request.getRequestDispatcher("/Dynamic/ForgotPassword.jsp").forward(request, response);
            return;
        }

        if (newPassword == null || newPassword.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Password cannot be empty.");
            request.getRequestDispatcher("/Dynamic/ResetPassword.jsp").forward(request, response);
            return;
        }

        boolean updated = userDAO.updatePassword(email, newPassword);

        if (updated) {
            session.invalidate();
            request.setAttribute("successMessage", "Password reset successfully.");
            request.getRequestDispatcher("/Dynamic/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Error resetting password. Email not found.");
            request.getRequestDispatcher("/Dynamic/ResetPassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
