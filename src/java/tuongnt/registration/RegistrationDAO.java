/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tuongnt.loginGoogle.UserGoogleDto;
import tuongnt.util.util;

/**
 *
 * @author tuong
 */
public class RegistrationDAO {

    public boolean checkLogin(String userName, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            con = util.makeConnection();
            String query = "Select * From registration WHERE username = ? And password = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, userName);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean isAdmin(String userName, String password) throws SQLException, ClassNotFoundException {
        boolean isAdmin = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = util.makeConnection();
            String query = "SELECT isAdmin FROM registration WHERE username = ? AND password = ?";
            st = con.prepareStatement(query);
            st.setString(1, userName);
            st.setString(2, password);
            rs = st.executeQuery();
            if (rs.next()) {
                isAdmin = rs.getBoolean("isAdmin");
            }
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

        return isAdmin;
    }
 public boolean isAdminV2(String userName) throws SQLException, ClassNotFoundException {
        boolean isAdmin = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = util.makeConnection();
            String query = "SELECT isAdmin FROM registration WHERE username = ?";
            st = con.prepareStatement(query);
            st.setString(1, userName);
            rs = st.executeQuery();
            if (rs.next()) {
                isAdmin = rs.getBoolean("isAdmin");
            }
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

        return isAdmin;
    }
    public String getUsername(String userName, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            con = util.makeConnection();
            String query = "SELECT username FROM registration WHERE username = ? AND password = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, userName);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null; // Return null if the username is not found
    }

    public List<RegistrationDTO> getListUser() throws SQLException, ClassNotFoundException {
        List<RegistrationDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = util.makeConnection();
            String query = "SELECT * FROM registration WHERE isAdmin = 0";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {

                RegistrationDTO user = new RegistrationDTO();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setLastname(rs.getString("lastname"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                list.add(user);
            }
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

        return list;
    }

    public void deleteAccount(String username) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String sql = "delete from registration where username = ?";
            con = util.makeConnection();
            st = con.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getUserId(String userName, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int userId = -1; // Giá trị mặc định nếu không tìm thấy

        try {
            con = util.makeConnection();
            String query = "SELECT user_id FROM registration WHERE username = ? AND password = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, userName);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            }
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return userId;
    }
public int getUserIdV2(String userName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int userId = -1; // Giá trị mặc định nếu không tìm thấy

        try {
            con = util.makeConnection();
            String query = "SELECT user_id FROM registration WHERE username = ? ";
            stm = con.prepareStatement(query);
            stm.setString(1, userName);
            rs = stm.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            }
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return userId;
    }
    public int countNonAdminUsers() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = util.makeConnection();
            String sql = "SELECT COUNT(user_id) AS total FROM registration WHERE isAdmin = 0";
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
    public boolean isDuplicateUser(String userName, String password) throws SQLException, ClassNotFoundException {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    boolean isDuplicate = false;

    try {
        con = util.makeConnection();
        String query = "SELECT COUNT(*) AS total FROM registration WHERE username = ? AND password = ?";
        stm = con.prepareStatement(query);
        stm.setString(1, userName);
        stm.setString(2, password);
        rs = stm.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("total");
            if (count > 0) {
                isDuplicate = true;
            }
        }
    } finally {
        // Đảm bảo đóng tất cả các tài nguyên
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    return isDuplicate;
}
       public boolean isDuplicateUserV2(String userName) throws SQLException, ClassNotFoundException {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    boolean isDuplicate = false;

    try {
        con = util.makeConnection();
        String query = "SELECT COUNT(*) AS total FROM registration WHERE username = ?";
        stm = con.prepareStatement(query);
        stm.setString(1, userName);
        rs = stm.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("total");
            if (count > 0) {
                isDuplicate = true;
            }
        }
    } finally {
        // Đảm bảo đóng tất cả các tài nguyên
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    return isDuplicate;
}
    public void insertUserFromGoogle(UserGoogleDto user) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = util.makeConnection();
            String query = "INSERT INTO registration (username, password, lastname,isAdmin) VALUES (?, ?, ?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getId());
            stmt.setString(3, user.getGiven_name());
            stmt.setBoolean(4, false);
            stmt.executeUpdate();
        } finally {
            // Close resources
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
     public void insertUserFromGoogleV2(UserGoogleDto user) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = util.makeConnection();
            String query = "INSERT INTO registration (username, lastname,isAdmin) VALUES (?, ?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getGiven_name());
            stmt.setBoolean(3, false);
            stmt.executeUpdate();
        } finally {
            // Close resources
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
     public boolean resetPassword(String username, String newPassword) throws SQLException, ClassNotFoundException {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        conn = util.makeConnection();
        String query = "UPDATE registration SET password = ? WHERE username = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, newPassword);
        stmt.setString(2, username);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } finally {
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
}
