package com.itheima.session.defaults;

import com.itheima.mapper.UserMapper;
import com.itheima.session.Configuration;
import com.itheima.session.SqlSession;
import com.itheima.session.mapper.Mapper;
import com.itheima.session.proxy.MapperProxy;
import com.itheima.session.util.Converter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/2 10:12
 * description:深圳黑马
 * version:1.0
 ******/
public class DefaultSqlSession implements SqlSession {

    //把Configuration对象给DefaultSqlSession
    private Configuration cfg;

    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }

    /***
     * 集合查询
     * @param key
     * @param <E>
     * @return
     */
    @Override
    public <E> List<E> list(String key) {
        try {
            //获取Connection
            Connection conn = cfg.getConnection();

            //获取Sql语句
            Mapper mapper = cfg.getMappers().get(key);
            String sql = mapper.getSql();

            //PreparedStatment
            PreparedStatement stm = conn.prepareStatement(sql);

            //执行SQl语句
            ResultSet resultSet = stm.executeQuery();

            //分析返回结果集
            List<E> list = Converter.list(resultSet,Class.forName(mapper.getResulttype()));

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <E> E getMapper(Class<E> clazz) {
        /****
         * 动态代理
         *  1)第一个参数是被代理的对象的类加载器
         *  2)被代理的接口数组：目的是让代理对象和被代理的接口拥有相同的行为动作(也就是支持相同的方法调用，类似给接口产生一个实现类)
         *  3)InvocationHandler:代理对象增强实现
         *
         */
        return (E) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MapperProxy(this));
    }

    @Override
    public void close() {

    }
}
