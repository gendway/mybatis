package com.itheima.service.impl;

import com.itheima.dao.UserInfoDao;
import com.itheima.domain.User;
import com.itheima.framework.factory.BeanFactory;
import com.itheima.service.UserService;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/1 8:50
 * description:深圳黑马
 * version:1.0
 ******/
public class UserInfoServiceImpl implements UserService {

    //耦合
    //private UserInfoDao userInfoDao = new UserInfoDaoImpl();
    private UserInfoDao userInfoDao = BeanFactory.getBean("userDao");

    @Override
    public List<User> list(){
        return userInfoDao.list();
    }

}
