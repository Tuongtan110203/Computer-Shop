/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tuong
 */
public class util {
    final  static private String DB_NAME = "seller";
    final  static private String DB_USER_NAME = "sa";
    final  static private String DB_PASSWORD = "12345";

    public static Connection makeConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        // buoc 1 load driver   
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //url pattern
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        //make connection
        con = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);
        return con;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println(util.makeConnection());
    }
}
