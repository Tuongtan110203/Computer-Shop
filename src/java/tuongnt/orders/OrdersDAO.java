package tuongnt.orders;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import tuongnt.cart.CartDAO;
import tuongnt.orderDetails.OrderDetailsDTO;
import tuongnt.util.util;

public class OrdersDAO {

    public boolean insertOrder(int userId, String productId) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            con = util.makeConnection();
            String insertOrderSql = "INSERT INTO orders (user_id, product_id, order_date,status) VALUES ( ?, ?, ?,?)";
            st = con.prepareStatement(insertOrderSql);
            st.setInt(1, userId);
            st.setString(2, productId);
            st.setString(3, formattedDateTime);
            st.setBoolean(4, false);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return false;
    }
    public boolean updateStatus(int user_id,String product_id) throws SQLException{
         Connection con = null;
        PreparedStatement st = null;
        boolean check = false;
        try {
            con = util.makeConnection();
            String sql = "update orders set status = 1 where user_id = ? and product_id = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setString(2, product_id);
           
            int rowEffected =  st.executeUpdate();
            if(rowEffected>0){
                return check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con!=null) con.close();
            if(st!=null) st.close();
        }
        return check;
    }
// Phương thức kiểm tra xem đơn hàng đã tồn tại hay chưa
    private boolean checkOrderExists(int userId, String productId) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = util.makeConnection();
            String sql = "SELECT * FROM orders WHERE user_id = ? AND product_id = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setString(2, productId);
            rs = st.executeQuery();

            return rs.next(); // Trả về true nếu đã tồn tại đơn hàng, ngược lại trả về false
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteOrder(int userId, String productId) throws SQLException {
        Connection con = null;
        PreparedStatement deleteCartSt = null;

        try {
            // Tạo kết nối
            con = util.makeConnection();

            // Chuẩn bị câu lệnh SQL để xóa sản phẩm từ bảng "cart"
            String deleteCartSql = "DELETE FROM orders WHERE user_id = ? AND product_id = ?";
            deleteCartSt = con.prepareStatement(deleteCartSql);
            deleteCartSt.setInt(1, userId);
            deleteCartSt.setString(2, productId);

            // Thực thi câu lệnh SQL và trả về số hàng được xóa
            int rowsAffected = deleteCartSt.executeUpdate();

            // Trả về true nếu có ít nhất một hàng đã được xóa, ngược lại trả về false
            return rowsAffected > 0;
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (deleteCartSt != null) {
                deleteCartSt.close();
            }
        }
        return false;
    }

    public boolean deleteOrderByUserId(int userId) throws SQLException {
        Connection con = null;
        PreparedStatement deleteCartSt = null;

        try {
            // Tạo kết nối
            con = util.makeConnection();

            // Chuẩn bị câu lệnh SQL để xóa sản phẩm từ bảng "cart"
            String deleteCartSql = "DELETE FROM orders WHERE user_id = ?";
            deleteCartSt = con.prepareStatement(deleteCartSql);
            deleteCartSt.setInt(1, userId);
            // Thực thi câu lệnh SQL và trả về số hàng được xóa
            int rowsAffected = deleteCartSt.executeUpdate();

            // Trả về true nếu có ít nhất một hàng đã được xóa, ngược lại trả về false
            return rowsAffected > 0;
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (deleteCartSt != null) {
                deleteCartSt.close();
            }
        }
        return false;
    }

    public int getOrderIdByUserId(int userId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement getOrderSt = null;
        ResultSet rs = null;
        int orderId = -1; // Giá trị mặc định nếu không tìm thấy orderId

        try {
            con = util.makeConnection();
            String getOrderSql = "SELECT order_id FROM orders WHERE user_id = ?";
            getOrderSt = con.prepareStatement(getOrderSql);
            getOrderSt.setInt(1, userId);
            rs = getOrderSt.executeQuery();

            // Kiểm tra xem có kết quả trả về hay không
            if (rs.next()) {
                orderId = rs.getInt("order_id");
            }
        } finally {
            // Đóng tất cả các tài nguyên
            if (rs != null) {
                rs.close();
            }
            if (getOrderSt != null) {
                getOrderSt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return orderId;
    }

    public String getOrderDateByProductId(String productId) throws SQLException {
        Connection conn = null;
        String orderDate = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = util.makeConnection(); // Khởi tạo kết nối
            String sql = "SELECT order_date FROM orders WHERE product_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                orderDate = rs.getString("order_date");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close(); // Đóng kết nối
            }
        }

        return orderDate;
    }

    public ArrayList<OrdersDTO> getOrderFullByUserId(int userId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<OrdersDTO> orderList = new ArrayList<>();

        try {
            con = util.makeConnection();
            String sql = "SELECT * FROM orders WHERE user_id = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrdersDTO order = new OrdersDTO();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setProductId(rs.getString("product_id"));
                
                order.setOrderDate(rs.getDate("order_date"));
                orderList.add(order);
            }
        } finally {
            // Đóng các đối tượng Connection, PreparedStatement và ResultSet
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderList;
    }
    public boolean deleteLatestOrder(int userId, String productId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = util.makeConnection();
            String deleteOrderSql = "DELETE FROM orders WHERE order_id = (SELECT TOP 1 order_id FROM orders WHERE user_id = ? AND product_id = ? ORDER BY order_id DESC)";
            st = con.prepareStatement(deleteOrderSql);
            st.setInt(1, userId);
            st.setString(2, productId);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception to handle it in the calling code
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public boolean updateOrdersQuantity(int userId, String productId, int newQuantity) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement updateSt = null;
        try {
            con = util.makeConnection();

            // Lấy giá của sản phẩm từ CartDAO
            CartDAO cartDAO = new CartDAO();
            double price = cartDAO.getProductPrice(productId);

            // Tính toán tổng tiền mới sau khi cập nhật số lượng
            double newTotalMoney = price * newQuantity;

            String updateSql = "UPDATE orders SET quantity = ?, total_money = ? WHERE user_id = ? AND product_id = ?";
            updateSt = con.prepareStatement(updateSql);
            updateSt.setInt(1, newQuantity);
            updateSt.setDouble(2, newTotalMoney);
            updateSt.setInt(3, userId);
            updateSt.setString(4, productId);
            int rowsAffected = updateSt.executeUpdate();
            return rowsAffected > 0;
        } finally {
            // Đóng các đối tượng Connection và PreparedStatement
            if (con != null) {
                con.close();
            }
            if (updateSt != null) {
                updateSt.close();
            }
        }
    }

}
