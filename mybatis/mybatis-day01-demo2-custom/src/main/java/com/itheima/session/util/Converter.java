package com.itheima.session.util;

import com.itheima.domain.User;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/2 11:52
 * description:深圳黑马
 * version:1.0
 ******/
public class Converter {

    /***
     * 类型转换你
     * @param resultSet
     * @param clazz
     * @param <E>
     * @return
     * @throws Exception
     */
    public static <E>List<E> list(ResultSet resultSet,Class clazz) throws Exception {
        //定义一个集合接受
        List<E> list = new ArrayList<E>();

        //获取当前Class的所有属性
        Field[] fields = clazz.getDeclaredFields();

        while (resultSet.next()){
            //将当前结果集转换成一个JavaBean
            Object instance = clazz.newInstance();
            for (Field field : fields) {
                //授权
                field.setAccessible(true);
                field.set(instance,resultSet.getObject(field.getName()));
            }

            list.add((E) instance);
        }
        return  list;
    }



}
