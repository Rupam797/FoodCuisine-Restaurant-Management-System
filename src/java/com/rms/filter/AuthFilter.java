package com.rms.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Security filter: Blocks unauthenticated access to Dynamic/* pages.
 * Allows access to login.jsp, signup.jsp, and related auth pages.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();

        // Allow access to auth-related and public pages without login
        if (uri.contains("login.jsp") || uri.contains("signup.jsp")
                || uri.contains("ForgotPassword.jsp") || uri.contains("VerifyOTP.jsp")
                || uri.contains("ResetPassword.jsp") || uri.contains("otpVerification.jsp")
                || uri.contains("EnterOTP.jsp")
                || uri.contains("Home.jsp") || uri.contains("menu.jsp")
                || uri.contains("about.jsp") || uri.contains("contact.jsp")
                || uri.contains("booking.jsp")) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userEmail") != null) {
            // User is authenticated — allow
            chain.doFilter(req, res);
        } else {
            // Not authenticated — redirect to login
            response.sendRedirect(request.getContextPath() + "/Dynamic/login.jsp");
        }
    }

    @Override
    public void destroy() {}
}
