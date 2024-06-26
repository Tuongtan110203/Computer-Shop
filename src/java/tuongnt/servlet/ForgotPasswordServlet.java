package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongnt.loginGoogle.JavaMailUtilsOTP;
import tuongnt.loginGoogle.OTPGenerator;

/**
 *
 * @author tuong
 */
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");

        if (!isValidEmail(email)) {
            request.setAttribute("error", "Invalid email address.Example xxxx@gmail.com or xxxx@gmail.vn or xxxx@fpt.edu.vn");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("email", email);

        try {
            String otp = OTPGenerator.generateOTP();
            JavaMailUtilsOTP.sendMail(email, otp);
            request.getSession().setAttribute("otp", otp);
            request.setAttribute("message", "OTP sent successfully. Please check your email.");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Failed to send OTP. Please try again later.");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        }
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(gmail\\.com|gmail\\.vn|fpt\\.edu\\.vn)$";
        return email.matches(regex);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
