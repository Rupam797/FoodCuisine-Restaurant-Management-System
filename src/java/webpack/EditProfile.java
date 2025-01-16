package webpack;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50   // 50MB
)
public class EditProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userEmail") == null) {
            response.sendRedirect("../Dynamic/login.jsp");
            return;
        }

        String userEmail = (String) session.getAttribute("userEmail");
        String newUsername = request.getParameter("username");
        String newAge = request.getParameter("age");
        String newGender = request.getParameter("gender");
        InputStream profilePicStream = null;

        if (request.getPart("profilePic") != null) {
            profilePicStream = request.getPart("profilePic").getInputStream();
        }

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");
             PreparedStatement pst = conn.prepareStatement(
                 "UPDATE USERS SET NAME = ?, AGE = ?, GENDER = ?, PROFILE_PIC = ? WHERE EMAIL = ?")) {

            pst.setString(1, newUsername);
            pst.setString(2, newAge);
            pst.setString(3, newGender);

            if (profilePicStream != null) {
                pst.setBlob(4, profilePicStream);
            } else {
                pst.setNull(4, java.sql.Types.BLOB);
            }

            pst.setString(5, userEmail);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                session.setAttribute("username", newUsername);
                session.setAttribute("userAge", newAge);
                session.setAttribute("userGender", newGender);

                response.getWriter().println("<script>alert('Profile updated successfully!');</script>");
                response.sendRedirect("../Dynamic/Home.jsp");
            } else {
                request.setAttribute("updateMessage", "Failed to update profile.");
                request.getRequestDispatcher("/Dynamic/EditProfile.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("updateMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/Dynamic/EditProfile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles profile updates for users";
    }
}
