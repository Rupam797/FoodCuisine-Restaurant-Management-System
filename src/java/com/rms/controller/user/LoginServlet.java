package com.rms.controller.user;

import com.rms.dao.UserDAO;
import com.rms.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Handles user login.
 * Replaces: webpack.LoginUser
 */
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("Useremail");
        String password = request.getParameter("Userpassword");

        User user = userDAO.login(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getName());
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userAge", String.valueOf(user.getAge()));
            session.setAttribute("userGender", user.getGender());
            session.setAttribute("userRole", user.getRole());

            if (user.isAdmin()) {
                // Admin user — redirect to admin dashboard
                response.sendRedirect(request.getContextPath() + "/Dynamic/ADashbord.jsp");
            } else {
                // Normal user — forward to Home
                request.setAttribute("loginMessage", "Login successful!");
                request.getRequestDispatcher("/Dynamic/Home.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("loginMessage", "Invalid email or password. Please try again.");
            request.getRequestDispatcher("/Dynamic/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
