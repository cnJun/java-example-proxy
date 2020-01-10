package com.sdk4.javaexample.test;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.dao.UserDao;
import com.sdk4.javaexample.dao.impl.UserDaoImpl;
import com.sdk4.javaexample.entity.User;
import com.sdk4.javaexample.util.ProxyUtils;

import java.util.List;
import java.util.Random;

/**
 * 动态代理
 */
public class DaoJdkProxyMain {

    public static void main(String[] args) {
        // 将saveGeneratedFiles设置为true，可以将生成的代理类保存到磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 目标对象
        UserDaoImpl dao = new UserDaoImpl();

        // 代理对象
        UserDao daoJdkProxy = ProxyUtils.createJdkProxy(dao);

        // 使用目标对象 dao 的地方，全部改为使用代理对象 daoJdkProxy，通过代理对象实现对目标对象的调用
        User user = new User();
        user.setName("SH-" + System.currentTimeMillis());
        user.setAge(new Random().nextInt(100));
        daoJdkProxy.save(user);

        List<User> list = daoJdkProxy.listAll();
        System.out.println(JSON.toJSONString(list, true));
    }

}
