package com.itheima.framework.jdbc;

import com.itheima.framework.util.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/7/31 3:40
 * description:深圳黑马
 * version:1.0
 ******/
public class Executor {

    /***
     * 集合查询
     * @param conn
     * @param <E>
     * @return
     */
    public static <E> List<E> list(Connection conn, String sql,Class clazz,Object... args) {
        //执行查询
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            //获取PreparedStatment
            stm = conn.prepareStatement(sql);

            //设置对应参数占位符
            if(args!=null && args.length>0){
                for (int i = 0; i < args.length; i++) {
                    stm.setObject((i+1),args[i]);
                }
            }

            //执行查询
            resultSet = stm.executeQuery();

            //调用Converter实现转换
            List<E> list = Converter.list(resultSet, clazz);

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(stm,conn,resultSet);
        }
    }


    /***
     * 查询一个
     * @param conn
     * @param sql
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> E one(Connection conn, String sql,Class clazz,Object... args) {
        //执行查询
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            //获取PreparedStatment
            stm = conn.prepareStatement(sql);

            //设置对应的占位符参数
            if(args!=null && args.length>0){
                for (int i = 0; i < args.length; i++) {
                    stm.setObject((i+1),args[i]);
                }
            }

            //执行查询
            resultSet = stm.executeQuery();

            //调用Converter实现转换
            return Converter.one(resultSet, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(stm,conn,resultSet);
        }
    }

    /***
     * 修改操作
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public static int update(Connection conn, String sql, Object... args) {
        return add(conn, sql, args);
    }

    /***
     * 执行增加操作
     * @param conn
     * @param sql
     * @param args  SQL语句所需要的占位符数据
     * @return
     */
    public static int add(Connection conn, String sql, Object... args) {
        //执行查询
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            //获取PreparedStatment
            stm = conn.prepareStatement(sql);

            if(args!=null && args.length>0){
                //循环设置参数
                for (int i = 0; i < args.length; i++) {
                    stm.setObject((i+1),args[i]);
                }
            }

            //执行增加操作
            return stm.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(stm,conn,resultSet);
        }
    }

    /**
     * 关闭资源
     * @param stm
     * @param conn
     */
    private static void close(PreparedStatement stm,Connection conn){
        close(stm,conn,null);
    }

    /***
     * 关闭资源
     * @param stm
     * @param conn
     * @param resultSet
     */
    private static void close(PreparedStatement stm,Connection conn,ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stm != null) {
                stm.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
