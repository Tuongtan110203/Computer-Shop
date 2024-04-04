package tuongnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tuongnt.cart.CartDAO;
import tuongnt.cart.CartDTO;
import tuongnt.orderDetails.OrderDetailsDAO;
import tuongnt.orders.OrdersDAO;

public class AddToCartServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        CartDAO cartDAO = new CartDAO();
        OrdersDAO ordersDAO = new OrdersDAO();

        // Thêm sản phẩm vào giỏ hàng
        boolean addToCartSuccess = false;
        try {
            addToCartSuccess = cartDAO.addToCart(userId, productId);
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }

        if (addToCartSuccess) {
            ArrayList<CartDTO> cart = (ArrayList<CartDTO>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }

            boolean found = false;
            for (CartDTO item : cart) {
                if (item.getUserId() == userId && item.getProductId().equals(productId)) {
                    item.setQuantity(item.getQuantity() + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartDTO(0, userId, productId, 1)); // Lưu userId vào giỏ hàng
            }
            OrdersDAO orderDAO = new OrdersDAO();
            try {
                for (CartDTO item : cart) {
                    if (item.getUserId() == userId && item.getProductId().equals(productId)) {
                        orderDAO.insertOrder(userId, productId);
                        break;
                    }
                }
            } catch (Exception e) {
            }
            int itemCount = 0;
            try {
                itemCount = cartDAO.getCartItemCount(userId);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            // Cập nhật userTotalQuantityMap
            Map<Integer, Integer> userTotalQuantityMap = (Map<Integer, Integer>) session.getAttribute("userTotalQuantityMap");
            if (userTotalQuantityMap == null) {
                userTotalQuantityMap = new HashMap<>();
            }
            userTotalQuantityMap.put(userId, itemCount);
            session.setAttribute("userTotalQuantityMap", userTotalQuantityMap);
            session.setAttribute("cart", cart);
            PrintWriter out = response.getWriter();
            for (Map.Entry<Integer, Integer> entry : userTotalQuantityMap.entrySet()) {
                out.println(entry.getValue());
                System.out.println(entry);
            }

        }

//       request.getRequestDispatcher("showUser.jsp").forward(request, response);
    }
}
