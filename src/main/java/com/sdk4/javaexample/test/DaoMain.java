package com.sdk4.javaexample.test;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.dao.UserDao;
import com.sdk4.javaexample.dao.impl.UserDaoImpl;
import com.sdk4.javaexample.entity.User;

import java.util.List;
import java.util.Random;

public class DaoMain {

    public static void main(String[] args) {
        UserDao dao = new UserDaoImpl();

        User user = new User();
        user.setName("SH-" + System.currentTimeMillis());
        user.setAge(new Random().nextInt(100));
        dao.save(user);

        List<User> list = dao.listAll();
        System.out.println(JSON.toJSONString(list, true));
    }

}
