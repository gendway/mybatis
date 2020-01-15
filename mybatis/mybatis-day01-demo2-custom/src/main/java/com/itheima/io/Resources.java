package com.itheima.io;

import java.io.InputStream;

/********
 * author:shenkunlin
 * date:2018/8/2 10:08
 * description:深圳黑马
 *              获取类路径你下文件字节输入流
 * version:1.0
 ******/
public class Resources {


    /****
     * 获取类路径下文件的字节输入流
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path) {
        //获取类加载器
        ClassLoader classLoader = Resources.class.getClassLoader();

        //获取文件字节输入流
        InputStream is = classLoader.getResourceAsStream(path);
        return is;
    }
}
