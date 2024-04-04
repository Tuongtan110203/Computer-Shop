/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongnt.products.ProductDAO;

/**
 *
 * @author tuong
 */
public class changeServlet extends HttpServlet {
    
    private String changeSuccess = "success.jsp";
    private String changeError = "error.jsp";
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
        String username = request.getParameter("user");
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");
        ProductDAO productDAO = new ProductDAO();
        String url = null;
        try {
            if (productDAO.isCorrectPassword(username, oldPass) && newPass.equals(confirmPass)) {
                productDAO.changePassword(username, newPass);
                request.getSession().setAttribute("changeSuccess", true);
                url = changeSuccess;
            } else {
                if (!productDAO.isCorrectPassword(username, oldPass)) {
                    request.getSession().setAttribute("errorMessage", "Incorrect old password");
                } else if (!newPass.equals(confirmPass)) {
                    request.getSession().setAttribute("errorMessage", "Passwords do not match");
                } else {
                    request.getSession().setAttribute("errorMessage", "Unknown error occurred");
                }
                request.getSession().setAttribute("changeSuccess", false);
                url = changeError;
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();

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
