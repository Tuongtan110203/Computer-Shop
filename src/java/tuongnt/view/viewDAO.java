/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tuongnt.util.util;

/**
 *
 * @author tuong
 */
public class viewDAO {
    public int getView() throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int totalView = 0;
        try {
            con = util.makeConnection();
            String sql = "SELECT viewed FROM [view]"; 
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                totalView = rs.getInt("viewed"); 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if(con!=null) con.close();
            if(st!=null) st.close();
            if(rs!=null) rs.close();
        }
        return totalView;
    }
     public void UpdateView() throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = util.makeConnection();
            String sql = "update [view] set viewed = viewed + 1 "; 
            st = con.prepareStatement(sql);
            st.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con!=null) con.close();
            if(st!=null) st.close();
        }
    }
}
