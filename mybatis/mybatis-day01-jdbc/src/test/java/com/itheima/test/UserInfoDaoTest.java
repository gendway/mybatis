package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.dao.impl.UserInfoDaoImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/7/31 19:43
 * description:深圳黑马
 * version:1.0
 ******/
public class UserInfoDaoTest {

    /***
     * 采用封装的工具包实现查询所有
     */
    @Test
    public void testList(){
        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        List<User> users = userInfoDao.list();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /****
     * 添加用户
     */
    @Test
    public void testadd(){
        //创建User对象
        User user = new User();
        user.setUsername("王宝强");
        user.setAddress("河南");
        user.setBirthday(new Date());
        user.setSex("男");

        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        userInfoDao.add(user);
    }

}
