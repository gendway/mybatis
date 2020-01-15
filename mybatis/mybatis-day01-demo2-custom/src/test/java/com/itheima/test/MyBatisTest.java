package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.io.Resources;
import com.itheima.mapper.UserMapper;
import com.itheima.session.SqlSession;
import com.itheima.session.SqlSessionFactory;
import com.itheima.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/2 9:11
 * description:深圳黑马
 * version:1.0
 ******/
public class MyBatisTest {

    @Test
    public void testFindAll() throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

        //创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        /***
         * 通过SqlSessionBuilder对象构建一个SqlSessionFactory
         * 接口
         */
        SqlSessionFactory sqlSessionFactory = builder.build(is);

        //通过SqlSessionFactory构建一个SqlSession
        //接口
        SqlSession session = sqlSessionFactory.openSession();

        /***
         * 通过SqlSession实现增删改查    创建一个代理对象   动态代理
         * UserMapper代理类具备访问数据库能力
         */
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();

        //打印输出
        for (User user : users) {
            System.out.println(user);
        }
        //关闭资源
        is.close();
        session.close();
    }


}
