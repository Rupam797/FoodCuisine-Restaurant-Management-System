package com.rms.controller.user;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Handles logout for both users and admins.
 * Replaces: webpack.Logout + adminpack.AdminLogout
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Set flash message in a new session for the toast notification
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("toastMessage", "Logged out successfully!");
        newSession.setAttribute("toastType", "info");
        response.sendRedirect(request.getContextPath() + "/Dynamic/Home.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
