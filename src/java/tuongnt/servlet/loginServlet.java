/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuongnt.registration.RegistrationDAO;
import tuongnt.view.viewDAO;

/**
 *
 * @author tuong
 */
public class loginServlet extends HttpServlet {

    private String adminPage = "DashBoard";
    private String userPage = "user";
    private String invalidPage = "Invalid.html";

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
        PrintWriter out = response.getWriter();
        try {
            RegistrationDAO dao = new RegistrationDAO();
            String userName = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            boolean isAdmin = dao.isAdmin(userName, password);
            String url = null;
            if (dao.checkLogin(userName, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("loginUsername", userName);
                session.setAttribute("passwordUsername", password);
                session.setAttribute("isAdminUsername", isAdmin);
                int userId = dao.getUserId(userName, password);
                session.setAttribute("userId", userId);
                if (isAdmin) {
                    url = adminPage;
                } else {
                    url = userPage;
                    viewDAO viewdao = new viewDAO();
                    viewdao.UpdateView();
                }
            } else {
                url = invalidPage;
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
