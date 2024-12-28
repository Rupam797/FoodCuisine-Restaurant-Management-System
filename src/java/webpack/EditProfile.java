package webpack;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import oracle.jdbc.OracleConnection;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50   // 50MB
)
public class EditProfile extends HttpServlet {

    private static final String UPLOAD_DIR = "profile";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userEmail") == null) {
            // If session is invalid, redirect to login page
            response.sendRedirect("../Dynamic/login.jsp");
            return;
        }

        // Get the session attributes
        String userEmail = (String) session.getAttribute("userEmail");

        // Get updated form data
        String newUsername = request.getParameter("username");
        String newAge = request.getParameter("age");
        String newGender = request.getParameter("gender");

                    // Get the uploaded file
            Part filePart = request.getPart("profile");
            String fileName = null;
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

            // Debugging the path
            System.out.println("Upload Path: " + uploadPath);

            // Ensure the directory exists
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            if (filePart != null && filePart.getSize() > 0) {
                fileName = new File(filePart.getSubmittedFileName()).getName();
                // Correct the file path creation
                String filePath = new File(uploadDir, fileName).getAbsolutePath();

                // Debugging the file path
                System.out.println("File Path: " + filePath);

                // Save the file
                filePart.write(filePath);
            }


        // Update the database
        OracleConnection oconn = null;
        PreparedStatement pst = null;

                try {
            // Registering Oracle Driver and establishing connection
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            oconn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            // SQL query
            String sql = "UPDATE USERS SET NAME = ?, AGE = ?, GENDER = ?, PROFILE_PIC = ? WHERE EMAIL = ?";
            pst = oconn.prepareStatement(sql);

            // Setting parameters
            pst.setString(1, newUsername);
            pst.setString(2, newAge);
            pst.setString(3, newGender);
            pst.setString(4, fileName != null ? UPLOAD_DIR + "/" + fileName : null); // Include relative path
            pst.setString(5, userEmail);

            // Debugging
            System.out.println("Executing SQL: " + pst.toString());

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                // Update session attributes
                session.setAttribute("username", newUsername);
                session.setAttribute("userAge", newAge);
                session.setAttribute("userGender", newGender);
                session.setAttribute("profilePic", fileName != null ? UPLOAD_DIR + "/" + fileName : null);

                // Redirect to Home.jsp with a success alert
                response.getWriter().println("<script type='text/javascript'>");
                response.getWriter().println("alert('Your profile has been updated successfully!');");
                response.getWriter().println("window.location.href = '/Dynamic/Home.jsp';");
                response.getWriter().println("</script>");
            } else {
                // Handle update failure
                request.setAttribute("updateMessage", "Failed to update profile. Please try again.");
                request.getRequestDispatcher("/Dynamic/EditProfile.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("updateMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/Dynamic/EditProfile.jsp").forward(request, response);
        }
         finally {
                    try {
                        if (pst != null) pst.close();
                        if (oconn != null) oconn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
