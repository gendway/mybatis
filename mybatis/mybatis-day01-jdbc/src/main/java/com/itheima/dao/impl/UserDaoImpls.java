package com.itheima.dao.impl;

import com.itheima.dao.UserInfoDao;
import com.itheima.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/7/31 19:16
 * description:深圳黑马
 * version:1.0
 ******/
public class UserDaoImpls implements UserInfoDao {

    /***
     * 查询用户
     * @return
     */
    @Override
    public List<User> list() {
        List<User> users = new ArrayList<User>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "123456");
            //定义 sql 语句 ?表示占位符
            //String sql = "select * from user where username = ?";
            //硬编码
            String sql = "select * from user";
            //获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);
            //向数据库发出 sql 执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //遍历查询结果集
            while (resultSet.next()) {
                //创建一个User对象
                User user = new User();
                //结果集解析存在硬编码
                //从ResultSet中取出数据，并将数据填充到user对象中
                user.setUsername(resultSet.getString("username"));
                user.setId(resultSet.getInt("id"));
                user.setSex(resultSet.getString("sex"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setAddress(resultSet.getString("address"));

                //将user对象加入到集合中
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return users;
    }





    /**
     * 缺陷：
     * 1)数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
     * 2)Sql 语句在代码中硬编码，造成代码不易维护，实际应用 sql 变化的可能较大， sql 变动需要改变java 代码。
     * 3)使用 preparedStatement 向占有位符号传参数存在硬编码，因为 sql 语句的 where 条件不一定，可能多也可能少，修改 sql 还要修改代码，系统不易维护。
     * 4)对结果集解析存在硬编码（查询列名）， sql 变化导致解析代码变化，系统不易维护，如果能将数据库记录封装成 pojo 对象解析比较方便。
     */
}
