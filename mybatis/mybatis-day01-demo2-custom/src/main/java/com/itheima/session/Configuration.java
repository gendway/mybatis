package com.itheima.session;

import com.itheima.session.mapper.Mapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/********
 * author:shenkunlin
 * date:2018/8/2 10:38
 * description:深圳黑马
 *          作用：
 *              1）封装数据库连接信息
 * version:1.0
 ******/
public class Configuration {
    //数据库驱动
    private String driver;
    //数据库连接地址
    private String url;
    //数据库账号
    private String username;
    //数据库密码
    private String password;

    //创建DataSource
    private ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //用来存储所有的SQL语句和对应的返回结果集转换的JavaBean全限定名
    private Map<String,Mapper> mappers = new HashMap<String,Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    //为了防止多次注入数据把已经存在的数据替换掉，这里改成putAll
    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers.putAll(mappers);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /***
     * 创建DataSource
     * @return
     */
    public DataSource getDataSource(){
        try {
            dataSource.setDriverClass(this.driver);
            dataSource.setJdbcUrl(this.url);
            dataSource.setUser(this.username);
            dataSource.setPassword(this.password);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /***
     * 创建数据库连接对象
     * @return
     */
    public Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }

}
