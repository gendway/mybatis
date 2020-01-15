package com.itheima.session;

import com.itheima.mapper.UserMapper;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/2 10:10
 * description:深圳黑马
 * version:1.0
 ******/
public interface SqlSession {


    /****
     * 具备查询数据库能力
     * @param <E>
     * @return
     */
    <E> List<E> list(String key);

    /***
     * 获得对应Class的代理对象
     * @param clazz
     * @param <E>
     * @return
     */
    <E> E getMapper(Class<E> clazz);

    void close();
}
