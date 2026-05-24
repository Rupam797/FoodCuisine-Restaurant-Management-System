package com.rms.controller.feedback;

import com.rms.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller: Handles complaint submissions.
 * Replaces: feedbackpack.Complain
 */
public class ComplainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("Fname");
        String phoneNo = request.getParameter("phno");
        String email = request.getParameter("Email");
        String subject = request.getParameter("Subject");
        String message = request.getParameter("Message");

        EmailUtil.sendComplaint(fullName, phoneNo, email, subject, message);

        response.sendRedirect(request.getContextPath() + "/Dynamic/Home.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
