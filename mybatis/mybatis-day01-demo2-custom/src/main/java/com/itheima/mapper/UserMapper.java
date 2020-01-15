package com.itheima.mapper;

import com.itheima.domain.User;
import com.itheima.session.annotation.Select;

import java.util.List;

/********
 * author:shenkunlin
 * date:2018/8/2 9:04
 * description:深圳黑马
 *              Dao接口层
 * version:1.0
 ******/
public interface UserMapper {

    /***
     * 用户查询
     * @return
     */
    @Select("SELECT * FROM  user")
    List<User> findAll();

    /**
     * 查询单个
     * @return
     */
    User getOne();
}
