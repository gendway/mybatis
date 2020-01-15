package com.itheima.session.defaults;

import com.itheima.session.SqlSession;
import com.itheima.session.SqlSessionFactory;
import com.itheima.session.util.XMLConfigBuilder;

import java.io.InputStream;

/********
 * author:shenkunlin
 * date:2018/8/2 10:12
 * description:深圳黑马
 * version:1.0
 ******/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    //主配置文件字节输入流
    private InputStream is;

    //set方法用于给is赋值，主要是SqlMapConfig.xml的字节输入流
    public void setIs(InputStream is) {
        this.is = is;
    }

    @Override
    public SqlSession openSession() {
        DefaultSqlSession sqlSession = new DefaultSqlSession();
        //调用解析XML文件   创建Configuration
        XMLConfigBuilder.loadConfiguration(sqlSession,is);
        return sqlSession;
    }
}
