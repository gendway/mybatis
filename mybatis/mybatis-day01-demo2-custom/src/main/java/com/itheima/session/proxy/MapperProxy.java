package com.itheima.session.proxy;

import com.itheima.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/2 12:08
 * description:深圳黑马
 * version:1.0
 ******/
public class MapperProxy implements InvocationHandler {

    //定义SqlSession，主要是为了调用它里面的list()
    private SqlSession sqlSession;

    //构造函数注入值
    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    /***
     * 代理实现
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取接口全限定名
        String className = method.getDeclaringClass().getName();

        //获取方法名
        String methodName = method.getName();

        String key = className+"."+methodName;

        //当method方法执行后返回List的时候，才调用list查询
        Class<?> returnType = method.getReturnType();
        if(returnType== List.class){
            return sqlSession.list(key);
        }else{
            //增删改
            return null;
        }

    }
}
