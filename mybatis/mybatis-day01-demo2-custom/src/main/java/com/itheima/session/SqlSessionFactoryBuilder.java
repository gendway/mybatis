package com.itheima.session;

import com.itheima.session.defaults.DefaultSqlSessionFactory;

import java.io.InputStream; /********
 * author:shenkunlin
 * date:2018/8/2 10:08
 * description:深圳黑马
 * version:1.0
 ******/
public class SqlSessionFactoryBuilder {


    public SqlSessionFactory build(InputStream is) {
        //创建SqlSessionFactory
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();

        //把主配置文件SqlMapConfig.xml的字节输入流给DefaultSqlSessionFactory
        sqlSessionFactory.setIs(is);

        return  sqlSessionFactory;
    }
}
