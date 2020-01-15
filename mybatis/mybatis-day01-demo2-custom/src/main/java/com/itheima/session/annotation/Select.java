package com.itheima.session.annotation;

import java.lang.annotation.*;

/********
 * author:shenkunlin
 * date:2018/8/2 12:26
 * description:深圳黑马
 * version:1.0
 ******/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Select {

    //用来存储SQL语句
    String value();
}
