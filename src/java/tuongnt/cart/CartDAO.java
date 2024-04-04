package tuongnt.cart;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tuongnt.util.util;

public class CartDAO {

    public boolean addToCart(int userId, String productId) throws SQLException {
        Connection con = null;
        PreparedStatement getProductSt = null;
        PreparedStatement addToCartSt = null;
        ResultSet rs = null;
        try {
            // Tạo kết nối
            con = util.makeConnection();
            // Truy vấn để lấy quantity từ bảng product
            String getProductSql = "SELECT quantity FROM products WHERE id = ?";
            getProductSt = con.prepareStatement(getProductSql);
            getProductSt.setString(1, productId);
            rs = getProductSt.executeQuery();

            // Kiểm tra xem sản phẩm có tồn tại và có số lượng không
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                if (quantity > 0) {
                    // Chuẩn bị câu lệnh SQL để kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
                    String checkCartSql = "SELECT * FROM cart WHERE user_id = ? AND product_id = ?";
                    PreparedStatement checkCartSt = con.prepareStatement(checkCartSql);
                    checkCartSt.setInt(1, userId);
                    checkCartSt.setString(2, productId);
                    ResultSet cartRs = checkCartSt.executeQuery();

                    if (cartRs.next()) {
                        // Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
                        int currentQuantity = cartRs.getInt("quantity");
                        if (currentQuantity > 0) {
                            String updateCartSql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
                            PreparedStatement updateCartSt = con.prepareStatement(updateCartSql);
                            updateCartSt.setInt(1, currentQuantity + 1);
                            updateCartSt.setInt(2, userId);
                            updateCartSt.setString(3, productId);
                            int rowsAffected = updateCartSt.executeUpdate();
                            return rowsAffected > 0;
                        } else {
                            // Nếu số lượng sản phẩm trong giỏ hàng là 0, xóa sản phẩm đó khỏi giỏ hàng
                            return deleteCartQuantity(userId, productId);
                        }

                    } else {
                        // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm mới vào giỏ hàng
                        String addToCartSql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
                        addToCartSt = con.prepareStatement(addToCartSql);
                        addToCartSt.setInt(1, userId);
                        addToCartSt.setString(2, productId);
                        addToCartSt.setInt(3, 1); // Số lượng mặc định là 1
                        int rowsAffected = addToCartSt.executeUpdate();
                        return rowsAffected > 0;
                    }
                }
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (getProductSt != null) {
                getProductSt.close();
            }
            if (addToCartSt != null) {
                addToCartSt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public ArrayList<CartDTO> getCartByUserId(int userId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CartDTO> cartList = new ArrayList<>();

        try {
            conn = util.makeConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Cart WHERE user_id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    CartDTO cartItem = new CartDTO();
                    cartItem.setUserId(rs.getInt("user_id"));
                    cartItem.setProductId(rs.getString("product_id"));
                    cartItem.setQuantity(rs.getInt("quantity"));
                    // Thêm mặt hàng vào danh sách giỏ hàng
                    cartList.add(cartItem);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cartList;
    }

    public double getProductPrice(String productId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement getPriceSt = null;
        ResultSet rs = null;
        double price = 0.0;

        try {
            con = util.makeConnection();
            String getPriceSql = "SELECT price FROM products WHERE ID = ?";
            getPriceSt = con.prepareStatement(getPriceSql);
            getPriceSt.setString(1, productId);
            rs = getPriceSt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (getPriceSt != null) {
                getPriceSt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return price;
    }

    public boolean deleteCartQuantity(int userId, String productId) throws SQLException {
        Connection con = null;
        PreparedStatement deleteCartSt = null;

        try {
            // Tạo kết nối
            con = util.makeConnection();

            // Chuẩn bị câu lệnh SQL để xóa sản phẩm từ bảng "cart"
            String deleteCartSql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
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

    public boolean deleteCartByUserId(int userId) throws SQLException {
        Connection con = null;
        PreparedStatement deleteCartSt = null;

        try {
            // Tạo kết nối
            con = util.makeConnection();

            // Chuẩn bị câu lệnh SQL để xóa sản phẩm từ bảng "cart"
            String deleteCartSql = "DELETE FROM cart WHERE user_id = ? ";
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

    public boolean updateCartQuantity(int userId, String productId, int quantity) throws SQLException {
        Connection con = null;
        PreparedStatement updateCartSt = null;

        try {
            // Tạo kết nối
            con = util.makeConnection();

            // Chuẩn bị câu lệnh SQL để cập nhật số lượng trong bảng "cart"
            String updateCartSql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
            updateCartSt = con.prepareStatement(updateCartSql);
            updateCartSt.setInt(1, quantity);
            updateCartSt.setInt(2, userId);
            updateCartSt.setString(3, productId);

            // Thực thi câu lệnh SQL và trả về số hàng được cập nhật
            int rowsAffected = updateCartSt.executeUpdate();

            // Trả về true nếu có ít nhất một hàng đã được cập nhật, ngược lại trả về false
            return rowsAffected > 0;
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (updateCartSt != null) {
                updateCartSt.close();
            }
        }
        return false;
    }

    public List<CartDTO> selectCart(int userId) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<CartDTO> cartList = new ArrayList<>();
        try {
            con = util.makeConnection();
            String sql = "SELECT * FROM cart where user_id = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();
            while (rs.next()) {
                int cartId = rs.getInt("cart_id");
                int cartUserId = rs.getInt("user_id");
                String productId = rs.getString("product_id");
                int quantity = rs.getInt("quantity");
                CartDTO cartDTO = new CartDTO(cartId, userId, productId, quantity);
                cartList.add(cartDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception properly
            }
        }
        return cartList;
    }

    public int getCartItemCount(int userId) throws ClassNotFoundException {
        int itemCount = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = util.makeConnection();

            String query = "SELECT sum(quantity) AS itemCount FROM cart WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                itemCount = resultSet.getInt("itemCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return itemCount;
    }

    public int getOrderIdsFromCart(String productId) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int orderId = -1; // Giá trị mặc định khi không có order_id nào được tìm thấy

    try {
        con = util.makeConnection();
        if (con != null) {
            String sql = "SELECT o.order_id FROM cart c JOIN orders o ON o.product_id = c.product_id WHERE c.product_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, productId); // Thiết lập tham số productId
            rs = ps.executeQuery();
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
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }
    return orderId;
}


    public Timestamp getOrderDatesByProductId(String productId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Timestamp orderDate = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = util.makeConnection();
            String sql = "SELECT o.order_date FROM cart c JOIN orders o ON c.product_id = o.product_id WHERE c.product_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                orderDate = rs.getTimestamp("order_date");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return orderDate;
    }
}
