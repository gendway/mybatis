package com.itheima.session.util;

import com.itheima.io.Resources;
import com.itheima.session.Configuration;
import com.itheima.session.annotation.Select;
import com.itheima.session.defaults.DefaultSqlSession;
import com.itheima.session.mapper.Mapper;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/********
 * author:shenkunlin
 * date:2018/8/2 10:15
 * description:深圳黑马
 *              作用：
 *                  1）解析SqlMapConfig.xml获取数据库连接信息
 * version:1.0
 ******/
public class XMLConfigBuilder {


    /***
     *
     * @param is  主配置文件的字节输入流
     */
    public static void loadConfiguration(DefaultSqlSession sqlSession, InputStream is){
        try {
            //创建Configuration对象
            Configuration cfg = new Configuration();

            //创建SAXReader
            SAXReader reader = new SAXReader();

            //加载主配置文件创建文档对象
            Document document = reader.read(is);

            //获取property所有结点信息  //property  表示获取任意结点下的property结点信息
            List<Element> propertyList = document.selectNodes("//property");

            //循环所有结点
            for (Element element : propertyList) {
                //获取每个结点上的name和value属性
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                if(name.equals("driver")){
                    //驱动
                    cfg.setDriver(value);
                }else if(name.equals("url")){
                    //数据库连接地址
                    cfg.setUrl(value);
                }else if(name.equals("username")){
                    //数据库账号
                    cfg.setUsername(value);
                }else if(name.equals("password")){
                    //密码
                    cfg.setPassword(value);
                }
            }

            //解析mappers结点
            List<Element> mapperlist = document.selectNodes("//mapper");

            //循环所有mapper结点，将resource的属性值传给laodMapper方法
            for (Element element : mapperlist) {
                //获取resource的属性值
                //String resource = element.attributeValue("resource");

                Attribute resourceAttribute = element.attribute("resource");
                //判断resouce属性的结点是否存在，如果存在则解析XML
                if(resourceAttribute!=null){
                    String resource = resourceAttribute.getValue();
                    //解析对应的UserMapper.xml
                    Map<String,Mapper> mappers = loadMapper(resource);

                    //把Mapper给Configuration
                    cfg.setMappers(mappers);
                }else{
                    //解析注解
                    String classpath = element.attributeValue("class");

                    //解析注解
                    Map<String, Mapper> mappers = loadAnnotationMapper(classpath);

                    cfg.setMappers(mappers);
                }
            }
            //把Configuration对象给DefaultSqlSession
            sqlSession.setCfg(cfg);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    /****
     * 解析对应接口上的注解
     * @param
     * @return
     */
    public  static Map<String, Mapper> loadAnnotationMapper(String classstr){
        try {
            //存储所有Mapper
            Map<String,Mapper> mappers = new HashMap<String,Mapper>();
            //获得Class
            Class clazz = Class.forName(classstr);

            //获取所有的方法，并循环
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                //如果方法上有注解，则开始解析
                Select annotation = method.getAnnotation(Select.class);

                if(annotation!=null){
                    //resultType  方法的返回泛型实际参数
                    Type type = method.getGenericReturnType();

                    //判断当前type是否是泛型实参
                    if(type instanceof ParameterizedType){
                        //把type墙砖成一个泛型实参
                        ParameterizedType ptype = (ParameterizedType) type;

                        //获取对应的泛型参数
                        Type[] types = ptype.getActualTypeArguments();


                        //取第一个实际泛型参数
                        Class domainClass = (Class) types[0];

                        //resultType
                        String resultType = domainClass.getName();

                        //获取Sql语句=注解的value属性
                        String sql = annotation.value();

                        //封装成一个Mapper
                        Mapper mapper = new Mapper(sql,resultType);

                        //key = 类的全限定名+.+方法名
                        mappers.put(method.getDeclaringClass().getName()+"."+method.getName(),mapper);
                    }

                    //SQL  resultType
                }
            }

            return mappers;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /****
     * 解析UserMapper.xml
     * @param path
     */
    public static Map<String, Mapper> loadMapper(String path){

        try {
            //定义一个Map存储解析的Mapper
            Map<String,Mapper> mappers = new HashMap<String,Mapper>();

            //获取文件字节输入流
            InputStream is = Resources.getResourceAsStream(path);

            //创建SAXReader
            SAXReader reader = new SAXReader();

            //加载文件字节输入流获取文件的文档对象
            Document document = reader.read(is);

            //解析mapper结点，获取namespace属性值
            Element rootElement = document.getRootElement();
            String namespace = rootElement.attributeValue("namespace");

            //获取select结点
            List<Element> selectList = document.selectNodes("//select");


            //循环select结点获取对应信息
            for (Element element : selectList) {
                //解析select结点 获取id、resulttype属性
                String id = element.attributeValue("id");
                String resultType = element.attributeValue("resultType");

                //获取select结点的内容  SQL语句
                String sql = element.getText();

                System.out.println("namespace:"+namespace+",id:"+id+",resultType:"+resultType+",sql:"+sql);

                //将sql语句和转换的类型存入MapperS
                Mapper mapper = new Mapper(sql, resultType);

                //存储Mapper
                //key=namespace+.+id
                mappers.put(namespace+"."+id,mapper);
            }

            return mappers;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }








    public static void main(String[] args) {
       /* InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        loadConfiguration(is);*/

        //loadMapper("com/itheima/mapper/UserMapper.xml");
    }
}
