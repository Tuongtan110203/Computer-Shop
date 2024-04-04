/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongnt.products.ProductDAO;
import tuongnt.products.ProductDTO;

/**
 *
 * @author tuong
 */
public class FetchKeyBoardByPriceServlet extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        ProductDAO pdao = new ProductDAO();
        try {
            List<ProductDTO> list = pdao.fetchKeyBoardByPrice(price);
        PrintWriter out = response.getWriter();
          for (ProductDTO product : list) {
                out.print("<div class=\"product col-md-6 col-lg-4 col-xl-4\">\n" +
                        "    <div class=\"rounded position-relative fruite-item\">\n" +
                        "        <div class=\"fruite-img\">\n" +
                        "            <img id=\"hwimg\" src=\"" + product.getImage() + "\" class=\"img-fluid w-100 rounded-top\" alt=\"\">\n");

                int categoryId = product.getCategory().getId();
                if (categoryId == 1) {
                    out.print("<div class=\"text-white bg-secondary px-3 py-1 rounded position-absolute\" style=\"top: 0; left: 0;\">Mouse</div>\n");
                } else if (categoryId == 2) {
                    out.print("<div class=\"text-white bg-secondary px-3 py-1 rounded position-absolute\" style=\"top: 0; left: 0;\">HeadPhone</div>\n");
                } else if (categoryId == 3) {
                    out.print("<div class=\"text-white bg-secondary px-3 py-1 rounded position-absolute\" style=\"top: 0; left: 0;\">KeyBoard</div>\n");
                }

                out.print("        </div>\n" +
                        "        <div class=\"p-4 border border-secondary border-top-0 rounded-bottom\">\n" +
                        "            <h4 class=\"text-center\">" + product.getName() + "</h4>\n" +
                        "            <div class=\"text-center\">\n");

                if (product.getQuantity() > 0) {
                    out.print("<span style=\"color: green;\">(Còn hàng)</span>\n");
                } else {
                    out.print("<span style=\"color: red;\">(Hết hàng)</span>\n");
                }

                out.print("<br>\n" +
                        "                <p class=\"text-dark fs-5 fw-bold mb-0\">\n" +
                        "                    " + formatPrice(product.getPrice()) + " VND\n" +
                        "                </p>\n" +
                        "            </div>\n" +
                        "            <button type=\"button\" class=\"btn border border-secondary rounded-pill px-3 text-primary addToCart\" onclick=\"addToCart('" + product.getId() + "')\"> \n" +
                        "                <i class=\"fa fa-shopping-bag me-2 text-primary\"></i>Add to Cart                           \n" +
                        "            </button>\n" +
                        "            <form id=\"add\" action=\"productDetail\" method=\"post\">\n" +
                        "                <input type=\"hidden\" name=\"userId\" value=\"" + request.getSession().getAttribute("userId") + "\">\n" +
                        "                <input type=\"hidden\" name=\"productId\" value=\"" + product.getId() + "\">\n" +
                        "                <a href=\"productDetail?id=" + product.getId() + "\" class=\"btn border border-secondary rounded-pill px-3 text-primary\" id=\"viewDetails\">View Details</a>\n" +
                        "            </form>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>");
            }
       } catch (Exception e) {
       }
        
    }
    private String formatPrice(double price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(price);
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
       try {
           processRequest(request, response);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(FetchKeyBoardByPriceServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(FetchKeyBoardByPriceServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
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
       try {
           processRequest(request, response);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(FetchKeyBoardByPriceServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(FetchKeyBoardByPriceServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
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
