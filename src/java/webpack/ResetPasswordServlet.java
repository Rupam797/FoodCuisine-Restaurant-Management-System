
package webpack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the new password entered by the user
        String newPassword = request.getParameter("password");

        // Normally, here you'd save the new password in the database, but we're just simulating it
        System.out.println("Password reset successfully to: " + newPassword);

        // Redirect to success page (could be a login page or confirmation page)
        response.sendRedirect("PasswordResetSuccess.jsp");
    }
}
