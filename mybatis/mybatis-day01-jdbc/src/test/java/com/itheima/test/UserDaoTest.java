package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/7/31 19:43
 * description:深圳黑马
 * version:1.0
 ******/
public class UserDaoTest {

    /***
     * 没有做任何封装
     */
    @Test
    public void testUserDao(){
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users = userDao.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

}
