/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuongnt.cart.CartDAO;
import tuongnt.cart.CartDTO;
import tuongnt.orderDetails.OrderDetailsDAO;
import tuongnt.orders.OrdersDAO;

/**
 *
 * @author tuong
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCartServlet at " + request.getContextPath() + "</h1>");
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
        String productId = request.getParameter("productId");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId"); // Get user ID from session
        int orderId = -1; // Default value if no orderId is found
        OrdersDAO ordersDAO = new OrdersDAO();
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        try {
            orderId = ordersDAO.getOrderIdByUserId(userId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (action != null && (action.equals("increase") || action.equals("decrease"))) {
            try {
                // Retrieve cart items for the current user
                CartDAO cartDAO = new CartDAO();
                OrdersDAO odersdao = new OrdersDAO();

                List<CartDTO> cart = cartDAO.selectCart(userId);
                for (CartDTO item : cart) {
                    if (item.getProductId().equals(productId)) {
                        try {
                            int newQuantity;
                            if (action.equals("increase")) {
                                item.setQuantity(item.getQuantity() + 1);
                                newQuantity = item.getQuantity();
                            } else { // action.equals("decrease")
                                newQuantity = Math.max(item.getQuantity() - 1, 0);
                                if (newQuantity == 0) {
                                    cartDAO.deleteCartQuantity(userId, productId);
                                    odersdao.deleteLatestOrder(userId, productId);
                                    cart.remove(item);
                                } else {
                                    item.setQuantity(newQuantity);
                                }
                            }
                            // Update quantity in the database
                            cartDAO.updateCartQuantity(userId, productId, newQuantity);
//                            ordersDAO.updateOrdersQuantity(userId, productId, newQuantity);                           
                        } catch (SQLException e) {
                            e.printStackTrace(); // Handle the error
                        }

                        break;
                    }
                }

                // Calculate total quantity of items in the cart for each user
                Map<Integer, Integer> userTotalQuantityMap = new HashMap<>();
                for (CartDTO item : cart) {
                    int quantity = item.getQuantity();
                    userTotalQuantityMap.put(userId, userTotalQuantityMap.getOrDefault(userId, 0) + quantity);
                }
                session.setAttribute("userTotalQuantityMap", userTotalQuantityMap);
                session.setAttribute("cart", cart);
                PrintWriter out = response.getWriter();
            for (Map.Entry<Integer, Integer> entry : userTotalQuantityMap.entrySet()) {
                out.println(entry.getValue());
            }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Forward the request to the cart.jsp page
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
