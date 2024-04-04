/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongnt.orderDetails.OrderDetailsDAO;
import tuongnt.orderDetails.OrderDetailsDTO;
import tuongnt.products.ProductDAO;
import tuongnt.registration.RegistrationDAO;
import tuongnt.view.viewDAO;

/**
 *
 * @author tuong
 */
public class DashBoardServlet extends HttpServlet {

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
            OrderDetailsDAO dao = new OrderDetailsDAO();
            List<OrderDetailsDTO> orderDetailsList1 = dao.selectOrderDetails();
            int totalOrder = dao.countTotalOrders();
            double totalMoney = 0;
            for (OrderDetailsDTO orderDetailsDTO : orderDetailsList1) {
                totalMoney += orderDetailsDTO.getTotalMoney();
            }
            ProductDAO productdao = new ProductDAO();
            int totalProducts = productdao.countProducts();
            RegistrationDAO regisdao = new RegistrationDAO();
            int totalNonAdminUsers = regisdao.countNonAdminUsers();
            viewDAO viewdao = new viewDAO();
            int totalView = viewdao.getView();
            request.getSession().setAttribute("totalView", totalView);
            request.getSession().setAttribute("totalNonAdminUsers", totalNonAdminUsers);
            request.getSession().setAttribute("totalProducts", totalProducts);
            request.getSession().setAttribute("totalOrder", totalOrder);
            request.getSession().setAttribute("totalMoney", totalMoney);
            request.getRequestDispatcher("DashBoard.jsp").forward(request, response);
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
