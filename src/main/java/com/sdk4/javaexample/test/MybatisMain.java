package com.sdk4.javaexample.test;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.entity.User;
import com.sdk4.javaexample.mapper.UserMapper;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisMain {

    public static void main(String[] args) {
        try {
            Reader reader = Resources.getResourceAsReader("Configuration.xml");
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                // 三种调用方式
                // 1. 直接使用 SqlSession 对象的方法
                User user1 = session.selectOne("com.sdk4.javaexample.mapper.UserMapper.getById", 1);
                System.out.println(JSON.toJSONString(user1));

                // 2. 获取 Mapper 的代理类
                UserMapper userMapper1 = session.getMapper(UserMapper.class);
                User user2 = userMapper1.getById(1);
                System.out.println(JSON.toJSONString(user2));

                // 3. 调用底层的方法创建代理类
                MapperProxyFactory<UserMapper> mapperProxyFactory = new MapperProxyFactory<>(UserMapper.class);
                UserMapper userMapper2 = mapperProxyFactory.newInstance(session);
                User user3 = userMapper2.getById(1);
                System.out.println(JSON.toJSONString(user3));

            } finally {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
