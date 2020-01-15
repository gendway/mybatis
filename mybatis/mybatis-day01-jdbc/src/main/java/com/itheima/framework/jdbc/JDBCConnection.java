package com.itheima.framework.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/********
 * author:shenkunlin
 * date:2018/7/31 19:41
 * description:深圳黑马
 * version:1.0
 ******/
public class JDBCConnection {


    static {
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "123456");
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }


    /*****
     * 组件：
     * Executor:做数据库操作
     * JDBCConnection:获取数据库连接对象
     * Converter:实现ResultSet结果集转换成对应JavaBean
     */
}
