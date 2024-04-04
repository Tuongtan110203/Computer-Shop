/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.orderDetails;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import tuongnt.cart.CartDAO;
import tuongnt.util.util;

/**
 *
 * @author tuong
 */
public class OrderDetailsDAO {

    public boolean insertOrderDetails(int orderId, String productId, String name, double price, String image, int quantity, double totalMoney, Timestamp orderDate, String fullname, String address,String email, String phone, String notice) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = util.makeConnection();
            String sql = "INSERT INTO orderdetails (order_id, product_id, name, price, image, quantity, total_money, order_date, fullname, address,email, phone,notice) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            stmt.setString(2, productId);
            stmt.setString(3, name);
            stmt.setDouble(4, price);
            stmt.setString(5, image);
            stmt.setInt(6, quantity);
            stmt.setDouble(7, totalMoney);
            stmt.setTimestamp(8, orderDate); // Sử dụng setTimestamp cho orderDate
            stmt.setString(9, fullname);
            stmt.setString(10, address);
            stmt.setString(11, email);
            stmt.setString(12, phone);
            stmt.setString(13, notice);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return success;
    }

    public ArrayList<OrderDetailsDTO> selectOrderDetails() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<OrderDetailsDTO> orderDetailsList = new ArrayList<>();

        try {
            // Tạo kết nối đến cơ sở dữ liệu
            conn = util.makeConnection();

            // Chuẩn bị câu lệnh SQL SELECT
            String sql = "SELECT * FROM orderdetails";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            // Xử lý kết quả trả về
            while (rs.next()) {
                // Tạo một đối tượng OrderDetailsDTO từ kết quả truy vấn
                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
                orderDetailsDTO.setOrderDetailId(rs.getInt("orderdetail_id"));
                orderDetailsDTO.setOrderId(rs.getInt("order_id"));
                orderDetailsDTO.setName(rs.getString("name"));
                orderDetailsDTO.setQuantity(rs.getInt("quantity"));
                orderDetailsDTO.setTotalMoney(rs.getDouble("total_money"));
                Timestamp timestamp = rs.getTimestamp("order_date");
                LocalDateTime orderDateTime = timestamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = orderDateTime.format(formatter);
                orderDetailsDTO.setOrderDate(formattedDateTime);
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String notice = rs.getString("notice");
                orderDetailsDTO.setFullname(fullname);
                orderDetailsDTO.setAddress(address);
                orderDetailsDTO.setPhone(phone);
                orderDetailsDTO.setNotice(notice);

                // Thêm orderDetailsDTO vào danh sách
                orderDetailsList.add(orderDetailsDTO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý các ngoại lệ nếu có
        } finally {
            // Đóng kết nối, statement và result set
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        // Trả về danh sách các đối tượng OrderDetailsDTO
        return orderDetailsList;
    }

    public int countTotalOrders() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int totalOrders = 0;

        try {
            conn = util.makeConnection();
            String sql = "SELECT COUNT(*) AS totalOrders FROM orderdetails";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt("totalOrders");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return totalOrders;
    }

    public int SumQuantityOrderDetails() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int SumQuantityOrderDetails = 0;

        try {
            conn = util.makeConnection();
            String sql = "SELECT Sum(quantity) AS SumQuantityOrderDetails FROM orderdetails";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                SumQuantityOrderDetails = rs.getInt("SumQuantityOrderDetails");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return SumQuantityOrderDetails;
    }

    public List<OrderDetailsDTO> selectTopSellingProducts() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OrderDetailsDTO> topProducts = new ArrayList<>();

        try {
            conn = util.makeConnection();
            String sql = "SELECT TOP 3 name, [image], price, product_id, SUM(quantity) AS total_quantity "
                    + "FROM orderdetails "
                    + "GROUP BY name, [image], price, product_id "
                    + "ORDER BY total_quantity DESC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetailsDTO product = new OrderDetailsDTO();
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getDouble("price"));
                product.setProductId(rs.getString("product_id"));
                topProducts.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return topProducts;
    }

    public int getOrderIdByProductId(String productId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int orderId = -1; // Giá trị mặc định khi không tìm thấy orderId

        try {
            conn = util.makeConnection();
            if (conn != null) {
                String sql = "SELECT order_id FROM orderdetails WHERE product_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, productId);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    orderId = rs.getInt("order_id");
                }
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
                conn.close();
            }
        }

        return orderId;
    }

}
