package com.rms.controller.admin;

import com.rms.dao.UserDAO;
import com.rms.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller: Admin user management (list, edit, delete).
 * Replaces: adminpack.EditUser, adminpack.DeleteUserServlet, and DB code in UserDetails.jsp
 */
public class ManageUsersServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/Dynamic/UserDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String email = request.getParameter("email");
            userDAO.deleteUser(email);
        } else if ("edit".equals(action)) {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String ageStr = request.getParameter("age");
            String gender = request.getParameter("gender");

            int age = 0;
            try { age = Integer.parseInt(ageStr); } catch (NumberFormatException e) { /* ignore */ }

            userDAO.updateUser(email, name, age, gender);
        }

        // Redirect back to the list
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
