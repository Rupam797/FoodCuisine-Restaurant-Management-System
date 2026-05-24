package com.rms.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Security filter: Blocks non-admin access to admin pages.
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        if (session != null && "admin".equals(session.getAttribute("userRole"))) {
            // User is admin — allow
            chain.doFilter(req, res);
        } else {
            // Not admin — redirect to home
            response.sendRedirect(request.getContextPath() + "/Dynamic/Home.jsp");
        }
    }

    @Override
    public void destroy() {}
}
