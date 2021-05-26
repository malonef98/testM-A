package com.example.DB;

import org.springframework.stereotype.Component;

import java.sql.*;
@Component
public class JDBCUtils {

    private static final String connectionURL = "jdbc:mysql://121.196.33.63:3310/Chaindigg?useUnicode=true&characterEncoding=UTF8&useSSL=false";
    private static final String username = "root";
    private static final String password = "123456";

    //创建数据库的连接
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return   DriverManager.getConnection(connectionURL,username,password);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    //关闭数据库的连接
    public static void close(ResultSet rs,Statement stmt,Connection con) throws SQLException {
        if(rs!=null)
            rs.close();
        if(stmt!=null)
            stmt.close();
        if(con!=null)
            con.close();
    }
}
