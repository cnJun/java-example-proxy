package com.sdk4.javaexample.test;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.dao.impl.UserDaoImpl;
import com.sdk4.javaexample.entity.User;
import com.sdk4.javaexample.proxy.UserDaoStaticProxy;

import java.util.List;
import java.util.Random;

/**
 * 静态代理
 */
public class DaoStaticProxyMain {
    public static void main(String[] args) {
        // 目标对象
        UserDaoImpl dao = new UserDaoImpl();

        // 代理对象
        UserDaoStaticProxy daoProxy = new UserDaoStaticProxy(dao);

        // 使用目标对象 dao 的地方，全部改为使用代理对象 daoProxy，通过代理对象实现对目标对象的调用
        User user = new User();
        user.setName("SH-" + System.currentTimeMillis());
        user.setAge(new Random().nextInt(100));
        daoProxy.save(user);

        List<User> list = daoProxy.listAll();
        System.out.println(JSON.toJSONString(list, true));
    }
}
