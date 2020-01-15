package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.framework.factory.BeanFactory;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/1 8:49
 * description:深圳黑马  依赖测试
 * version:1.0
 ******/
public class DiTest {

    @Test
    public void testDi(){
        //依赖
        //UserService userService = new UserServiceImpl();
        UserService userService = BeanFactory.getBean("userService");


        List<User> users = userService.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

}
