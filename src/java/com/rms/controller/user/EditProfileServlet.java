package com.rms.controller.user;

import com.rms.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Controller: Handles profile updates.
 * Replaces: webpack.EditProfile
 */
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class EditProfileServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userEmail") == null) {
            response.sendRedirect(request.getContextPath() + "/Dynamic/login.jsp");
            return;
        }

        String userEmail = (String) session.getAttribute("userEmail");
        String newUsername = request.getParameter("username");
        String newAge = request.getParameter("age");
        String newGender = request.getParameter("gender");

        InputStream profilePicStream = null;
        Part profilePart = request.getPart("profilePic");
        if (profilePart != null && profilePart.getSize() > 0) {
            profilePicStream = profilePart.getInputStream();
        }

        int age = 0;
        try {
            age = Integer.parseInt(newAge);
        } catch (NumberFormatException e) {
            // keep 0
        }

        boolean updated = userDAO.updateProfile(userEmail, newUsername, age, newGender, profilePicStream);

        if (updated) {
            session.setAttribute("username", newUsername);
            session.setAttribute("userAge", newAge);
            session.setAttribute("userGender", newGender);
            response.sendRedirect(request.getContextPath() + "/Dynamic/Home.jsp");
        } else {
            request.setAttribute("updateMessage", "Failed to update profile.");
            request.getRequestDispatcher("/Dynamic/EditProfile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
