package webpack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OTPVerify extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        int sessionOtp = (int) session.getAttribute("otp");
        String email = (String) session.getAttribute("email");

        try {
            int enteredOtp = Integer.parseInt(request.getParameter("otp"));

            if (enteredOtp == sessionOtp) {
                request.setAttribute("email", email);
                System.out.println("Email: " + email);
                request.getRequestDispatcher("/Dynamic/ResetPassword.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid OTP. Please try again.");
                request.getRequestDispatcher("/Dynamic/VerifyOTP.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid OTP format. Please enter a valid OTP.");
            request.getRequestDispatcher("/Dynamic/VerifyOTP.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Verifies the OTP entered by the user.";
    }
}
