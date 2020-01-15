package com.itheima.framework.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/********
 * author:shenkunlin
 * date:2018/8/1 8:40
 * description:深圳黑马
 * version:1.0
 ******/
public class BeanFactory {
    //创建一个Properties对象
    private static Properties prop = new Properties();

    static {
        try {
            //获取配置文件字节输入流
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            //将配置文件加载到prop中
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 根据Bean的名字获取Bean的实例
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName){
        try {
            //获取对应的类全限定名
            String classname = (String) prop.get(beanName);
            //获取对应Bean的全限定名
            Class<?> clazz = Class.forName(classname);
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
