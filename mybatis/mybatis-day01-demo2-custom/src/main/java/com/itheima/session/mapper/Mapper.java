package com.itheima.session.mapper;

/********
 * author:shenkunlin
 * date:2018/8/2 11:02
 * description:深圳黑马
 *          存储SQL语句和执行SQL语句之后数据结果集封装要转换的对象的类型全限定名
 * version:1.0
 ******/
public class Mapper {

    //SQL语句
    private String sql;
    //结果集要转换的类型全限定名
    private String resulttype;

    public Mapper(String sql, String resulttype) {
        this.sql = sql;
        this.resulttype = resulttype;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResulttype() {
        return resulttype;
    }

    public void setResulttype(String resulttype) {
        this.resulttype = resulttype;
    }
}
