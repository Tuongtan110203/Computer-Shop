/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tuongnt.util.util;

/**
 *
 * @author tuong
 */
public class CategoryDAO {
    public List<CategoryDTO> getAllCategory() throws SQLException  {
        List<CategoryDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = util.makeConnection();
            String sql = "select * from Categories";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                CategoryDTO c = new CategoryDTO(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("describe"));
                list.add(c);
            }
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        } finally{
            if(con!=null) con.close();
            if(st != null) st.close();
            if(rs!=null) rs.close();
        }
        return list;
    }
    public CategoryDTO getCategoryById(int id){
         Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = util.makeConnection();
            String sql = "select * from categories where id=?";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                return new CategoryDTO(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("describe"));
            }
        } catch (Exception e) {
        }
        return null;
    }
}
