package com.rms.controller.user;

import com.rms.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Controller: Fetches and streams the user profile picture from the database.
 * Falls back to the default profile circle image if none is set.
 */
public class ProfilePicServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");

        // Fallback to session email if parameter is not supplied or empty
        if (email == null || email.trim().isEmpty()) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                email = (String) session.getAttribute("userEmail");
            }
        }

        byte[] imgBytes = null;
        if (email != null && !email.trim().isEmpty()) {
            imgBytes = userDAO.getProfilePic(email.trim());
        }

        // Prevent browser caching so that new uploads reflect instantly
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (imgBytes != null && imgBytes.length > 0) {
            response.setContentType("image/jpeg");
            response.setContentLength(imgBytes.length);
            try (OutputStream out = response.getOutputStream()) {
                out.write(imgBytes);
                out.flush();
            }
        } else {
            // Serve the default placeholder image from Web application path
            response.setContentType("image/jpeg");
            try (InputStream in = getServletContext().getResourceAsStream("/Images/profile-circle.jpg");
                 OutputStream out = response.getOutputStream()) {
                if (in != null) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    out.flush();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Default image not found");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
