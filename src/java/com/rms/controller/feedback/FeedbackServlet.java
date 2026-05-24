package com.rms.controller.feedback;

import com.rms.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Handles feedback submissions.
 * Replaces: feedbackpack.Feedback
 */
public class FeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("Fname");
        String lastName = request.getParameter("Lname");
        String email = request.getParameter("Email");
        String subject = request.getParameter("Subject");
        String message = request.getParameter("Message");

        EmailUtil.sendFeedback(firstName, lastName, email, subject, message);

        response.sendRedirect(request.getContextPath() + "/Dynamic/Home.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
