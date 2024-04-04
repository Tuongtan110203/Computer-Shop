/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongnt.cart.CartDAO;
import tuongnt.cart.CartDTO;
import tuongnt.loginGoogle.JavaMailUtils;
import tuongnt.orderDetails.OrderDetailsDAO;
import tuongnt.orderDetails.OrderDetailsDTO;
import tuongnt.orders.OrdersDAO;
import tuongnt.orders.OrdersDTO;
import tuongnt.products.ProductDAO;

/**
 *
 * @author tuong
 */
public class checkoutServlet extends HttpServlet {

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
            out.println("<title>Servlet checkoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkoutServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        int userId = (int) request.getSession().getAttribute("userId");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String notice = request.getParameter("notice");
        String email = request.getParameter("userEmail");

        CartDAO cartDAO = new CartDAO();
        ArrayList<CartDTO> cartList = null;
        try {
            cartList = cartDAO.getCartByUserId(userId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        ProductDAO productDAO = new ProductDAO();
        OrdersDAO orderDAO = new OrdersDAO();

        try {
            for (CartDTO cartDTO : cartList) {
                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
                String productId = cartDTO.getProductId();
                int orderId = cartDAO.getOrderIdsFromCart(productId);
                String productName = productDAO.getProductNameByProductId(productId);
                double productPrice = productDAO.getProductPrice(productId);
                String productImage = productDAO.getProductImageByProductId(productId);
                int quantity = cartDTO.getQuantity();
                double totalMoney = cartDTO.getQuantity() * productDAO.getProductPrice(productId);
                Timestamp orderDate = cartDAO.getOrderDatesByProductId(productId);
                boolean success = orderDetailsDAO.insertOrderDetails(orderId, productId, productName, productPrice, productImage, quantity,
                        totalMoney, orderDate, fullName, address, email, phone, notice);
                boolean updateStatus = orderDAO.updateStatus(userId, productId);
                JavaMailUtils.sendMail(email, productName, productPrice,quantity, totalMoney);

            }
            request.getSession().setAttribute("orderList", cartList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<CartDTO> cart = (ArrayList<CartDTO>) request.getSession().getAttribute("cart");
        ArrayList<CartDTO> userCart = new ArrayList<>();
        for (CartDTO item : cart) {
            if (item.getUserId() == userId) {
                userCart.add(item);
            }
        }
        for (CartDTO item : userCart) {
            productDAO.updateProductQuantity(item.getQuantity(), item.getProductId());
        }
        Map<Integer, Integer> userTotalQuantityMap = new HashMap<>();
        userTotalQuantityMap.put((Integer) request.getSession().getAttribute("userId"), 0);
        request.getSession().setAttribute("userTotalQuantityMap", userTotalQuantityMap);
        try {
            cartDAO.deleteCartByUserId(userId);
//            orderDAO.deleteOrderByUserId(userId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        request.getSession().removeAttribute("cart");
        request.setAttribute("fullName", fullName);
        request.setAttribute("address", address);
        request.setAttribute("phone", phone);
        request.getRequestDispatcher("showOrderUser.jsp").forward(request, response);
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
