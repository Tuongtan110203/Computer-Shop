/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tuongnt.categories.CategoryDAO;
import tuongnt.categories.CategoryDTO;
import tuongnt.util.util;

/**
 *
 * @author tuong
 */
public class ProductDAO {

    public List<ProductDTO> getAllProduct() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = util.makeConnection();
            String sql = "select * from products";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO c = new CategoryDAO();
                CategoryDTO category = c.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public List<ProductDTO> getTop6Product() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = util.makeConnection();
            String sql = "select top 6 * from products";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO c = new CategoryDAO();
                CategoryDTO category = c.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public List<ProductDTO> getNext3Product(int amount) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = util.makeConnection();
            String sql = "select * from products order by id offset ? rows fetch next 6 row only";
            st = con.prepareStatement(sql);
            st.setInt(1, amount);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO c = new CategoryDAO();
                CategoryDTO category = c.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public void addProduct(String id, String name,
            int quantity, double price,
            String releaseDate, String describe,
            String image, int categoryId) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = util.makeConnection();
            String sql = "INSERT INTO products (id, name, quantity, price, releaseDate, describe, image, cid) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(sql);
            st.setString(1, id);
            st.setNString(2, name);

            st.setInt(3, quantity);
            st.setDouble(4, price);
            st.setString(5, releaseDate);
            st.setNString(6, describe);
            st.setString(7, image);
            st.setInt(8, categoryId);
            st.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<ProductDTO> searchProductByName(String name) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "select * from products where name like ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            rs = st.executeQuery();
            while (rs.next()) {

                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteProduct(String id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            String sql = "delete from products where id = ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> searchProductById(String id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "select * from products where id like ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setString(1, id + "%");
            rs = st.executeQuery();
            while (rs.next()) {

                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getProductIdByOrderId(int orderId) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String productId = null;

        try {
            conn = util.makeConnection();
            String sql = "SELECT product_id FROM orders WHERE order_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                productId = rs.getString("product_id");
            }
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

        return productId;
    }

    public List<ProductDTO> searchProductByPriceRange(double priceFrom, double priceTo) {
        // Thực hiện tìm kiếm theo khoảng giá
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM products WHERE price >= ? AND price <= ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setDouble(1, priceFrom);
            st.setDouble(2, priceTo);
            rs = st.executeQuery();

            while (rs.next()) {
                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ProductDTO> searchProductByReleaseDate(String releaseDate) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "select * from products where releaseDate like ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setString(1, releaseDate + "%");
            rs = st.executeQuery();
            while (rs.next()) {

                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isProductIdExists(String id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            con = util.makeConnection();

            String query = "SELECT COUNT(*) FROM products WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return false;
    }

    public void updateProduct(String name,
            int quantity, double price,
            String releaseDate, String describe,
            String image, String id) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            String sql = "update products set name = ?, quantity = ?, price = ?, "
                    + "releaseDate = ?,describe = ?,image= ? where ID = ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, quantity);
            st.setDouble(3, price);
            st.setString(4, releaseDate);
            st.setString(5, describe);
            st.setString(6, image);
            st.setString(7, id);

            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getProductPrice(String productId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement getPriceSt = null;
        ResultSet rs = null;
        double price = 0.0;

        try {
            // Tạo kết nối
            con = util.makeConnection();

            // Truy vấn giá của sản phẩm từ cơ sở dữ liệu
            String getPriceSql = "SELECT price FROM products WHERE ID = ?";
            getPriceSt = con.prepareStatement(getPriceSql);
            getPriceSt.setString(1, productId);
            rs = getPriceSt.executeQuery();

            // Lấy giá của sản phẩm từ kết quả truy vấn
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        } finally {
            // Đóng các đối tượng ResultSet, PreparedStatement và Connection
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

    public ProductDTO getProductById(String id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String sql = "select * from products where id = ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                return new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<String> getProductIdsInCart() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> productIds = new ArrayList<>();

        try {
            con = util.makeConnection(); // Thay thế DBUtils bằng lớp kết nối của bạn
            if (con != null) {
                String sql = "SELECT product_id FROM products p JOIN cart c ON c.product_id = p.ID";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    String productId = rs.getString("product_id");
                    productIds.add(productId);
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

        return productIds;
    }

    public String getProductImageByProductId(String id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String image = null;

        try {
            conn = util.makeConnection();
            String sql = "SELECT image FROM products WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                image = rs.getString("image");
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

        return image;
    }

    public void register(String username, String password, String lastname, boolean isAdmin) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = util.makeConnection();
            String sql = "INSERT INTO registration (username, password,lastname,isAdmin) VALUES (?, ?,?,?)";
            st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, lastname);
            st.setBoolean(4, isAdmin);
            st.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameExists(String username) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = util.makeConnection();
            String sql = "SELECT username FROM registration WHERE username=?";
            st = con.prepareStatement(sql);
            st.setString(1, username);
            rs = st.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

            return false;
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
                e.printStackTrace();
            }
        }
    }

    public List<ProductDTO> fetchProductsByPrice(double price) throws ClassNotFoundException, SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = util.makeConnection();
            String query = "SELECT * FROM products WHERE price <= ?";
            ps = conn.prepareStatement(query);
            ps.setDouble(1, price);
            rs = ps.executeQuery();

            while (rs.next()) {

                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                productList.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return productList;
    }

    public List<ProductDTO> fetchMouseByPrice(double price) throws ClassNotFoundException, SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = util.makeConnection();
            String query = "SELECT * FROM products WHERE price <= ? and id like 'C%'";
            ps = conn.prepareStatement(query);
            ps.setDouble(1, price);
            rs = ps.executeQuery();

            while (rs.next()) {

                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                productList.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return productList;
    }

    public List<ProductDTO> fetchKeyBoardByPrice(double price) throws ClassNotFoundException, SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = util.makeConnection();
            String query = "SELECT * FROM products WHERE price <= ? and id like 'B%'";
            ps = conn.prepareStatement(query);
            ps.setDouble(1, price);
            rs = ps.executeQuery();

            while (rs.next()) {

                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                productList.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return productList;
    }

    public List<ProductDTO> fetchHeadPhoneByPrice(double price) throws ClassNotFoundException, SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = util.makeConnection();
            String query = "SELECT * FROM products WHERE price <= ? and id like 'T%'";
            ps = conn.prepareStatement(query);
            ps.setDouble(1, price);
            rs = ps.executeQuery();

            while (rs.next()) {

                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                productList.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return productList;
    }

    public void changePassword(String username, String newPassword) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = util.makeConnection();
            String sql = "UPDATE registration SET password = ? WHERE username = ?";
            st = con.prepareStatement(sql);
            st.setString(1, newPassword);
            st.setString(2, username);
            st.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isCorrectPassword(String username, String password) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = util.makeConnection();
            String sql = "SELECT * FROM registration WHERE username=? AND password=?";
            st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            rs = st.executeQuery();

            return rs.next(); // Returns true if a matching record is found
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
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
                e.printStackTrace();
            }
        }
    }

    public List<ProductDTO> searchProductByMouse() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products p JOIN Categories c ON c.ID = p.cid WHERE p.name LIKE N'Mouse%'";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tất cả các tài nguyên
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
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<ProductDTO> searchProductByKeyBoard() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products p JOIN Categories c ON c.ID = p.cid WHERE p.name LIKE N'Key Board%'";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tất cả các tài nguyên
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
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<ProductDTO> searchProductByHeadPhone() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products p JOIN Categories c ON c.ID = p.cid WHERE p.name LIKE N'Head Phone%'";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tất cả các tài nguyên
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
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<ProductDTO> searchProductByALL() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products p JOIN Categories c ON c.ID = p.cid WHERE c.ID in(1,2,3)";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = dao.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category);
                list.add(p);
            }
        } catch (Exception e) {
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
                e.printStackTrace();
            }
        }
        return list;
    }

    public void updateProductQuantity(int quantity, String pid) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = util.makeConnection();
            String query = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, quantity);
            stmt.setString(2, pid);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProductNameByProductId(String productId) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String productName = null;

        try {
            con = util.makeConnection();
            String sql = "SELECT name FROM products WHERE ID = ?";
            st = con.prepareStatement(sql);
            st.setString(1, productId);
            rs = st.executeQuery();

            if (rs.next()) {
                productName = rs.getString("name");
            }
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

        return productName;
    }

    public int countProducts() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = util.makeConnection();
            String sql = "SELECT COUNT(ID) AS total FROM products";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("total");
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

        return count;
    }

    public List<ProductDTO> getSimilarProducts(String productId) throws SQLException {
        List<ProductDTO> similarProducts = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = util.makeConnection();
            String sql = "SELECT * FROM products WHERE ID LIKE ? AND ID <> ?";
            st = con.prepareStatement(sql);
            st.setString(1, productId.substring(0, 1) + "%"); // Lấy chữ cái đầu tiên của ID để tìm kiếm các sản phẩm tương tự
            st.setString(2, productId); // Không lấy sản phẩm hiện tại
            rs = st.executeQuery();

            while (rs.next()) {
                CategoryDAO c = new CategoryDAO();
                CategoryDTO category = c.getCategoryById(rs.getInt("cid"));
                ProductDTO p = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("releaseDate"),
                        rs.getString("describe"),
                        rs.getString("image"),
                        category
                );
                similarProducts.add(p);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return similarProducts;
    }
}
