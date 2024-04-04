/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongnt.categories.CategoryDAO;
import tuongnt.categories.CategoryDTO;
import tuongnt.products.ProductDAO;

/**
 *
 * @author tuong
 */
public class addServlet extends HttpServlet {

    private String adminPage = "admin";

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
            out.println("<title>Servlet addServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String url = adminPage;
            String id = request.getParameter("id");
            if (!isValidId(id)) {
                request.setAttribute("idError", "Invalid ID format. ID must start with B, T, or C followed by two digits.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
                dispatcher.forward(request, response);
                return;
            }
            if (isIdDuplicate(id)) {
                request.setAttribute("idError", "ID already exists. Please choose a different one.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
                dispatcher.forward(request, response);
                return;
            }

            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String releaseDateStr = request.getParameter("releasedate");
            String describe = request.getParameter("describe");
            String image = request.getParameter("image");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            if (!isValidCategoryForId(id, categoryId)) {
                request.setAttribute("idError", "Category must match with ID. B = 3, T = 2, C = 1");
                RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
                dispatcher.forward(request, response);
                return;
            }
         
            ProductDAO pdao = new ProductDAO();
            CategoryDAO cdao = new CategoryDAO();
            CategoryDTO c = cdao.getCategoryById(categoryId);
            pdao.addProduct(id, name, quantity, price, releaseDateStr, describe, image, c.getId());

            response.sendRedirect(url);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isValidId(String id) {
        String regex = "^[BTC]\\d{1,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

   
    private boolean isIdDuplicate(String id) throws SQLException, ClassNotFoundException {

        ProductDAO productDAO = new ProductDAO();
        return productDAO.isProductIdExists(id);
    }

    private boolean isValidCategoryForId(String id, int categoryId) {
        if (id.startsWith("B") && categoryId == 3) {
            return true;
        } else if (id.startsWith("T") && categoryId == 2) {
            return true;
        } else if (id.startsWith("C") && categoryId == 1) {
            return true;
        }
        return false;
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
