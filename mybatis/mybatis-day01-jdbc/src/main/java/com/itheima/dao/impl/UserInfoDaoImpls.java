package com.itheima.dao.impl;

import com.itheima.dao.UserInfoDao;
import com.itheima.domain.User;
import com.itheima.framework.jdbc.Executor;
import com.itheima.framework.jdbc.JDBCConnection;

import java.util.Date;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/7/31 19:46
 * description:深圳黑马
 * version:1.0
 ******/
public class UserInfoDaoImpls implements UserInfoDao {


    /***
     * 集合查询
     * @return
     */
    @Override
    public List<User> list(){
        //SQL语句
        String sql="select * from user";
        return Executor.list(JDBCConnection.getConnection(), sql, User.class);
    }


    /***
     * 增加用户
     * @return
     */
    public int add(User user){
        //准备的SQL语句
        String sql="insert into user(username,birthday,sex,address)values(?,?,?,?)";
        return Executor.add(JDBCConnection.getConnection(),sql,user.getUsername(),user.getBirthday(),user.getSex(),user.getAddress());
    }



}
