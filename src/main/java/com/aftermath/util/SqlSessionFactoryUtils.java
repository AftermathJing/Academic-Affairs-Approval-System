package com.aftermath.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    // 将SqlSessionFactory设置为静态成员变量，确保只会创建一次
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try (InputStream inputStream = Resources.getResourceAsStream("com/mybatis-config.xml")) {
            // 将创建的SqlSessionFactory对象赋值给静态成员变量
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 直接返回静态成员变量即可
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}


