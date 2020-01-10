package com.sdk4.javaexample.proxy;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.dao.UserDao;
import com.sdk4.javaexample.entity.User;
import com.sdk4.javaexample.util.LogUtils;

import java.util.List;

/**
 * 代理类
 */
public class UserDaoStaticProxy implements UserDao {
    // 目标对象
    private UserDao target;

    public UserDaoStaticProxy(UserDao userDao) {
        this.target = userDao;
    }

    @Override
    public boolean save(User user) {
        long start = System.currentTimeMillis();

        // 调用目标对象
        boolean ret = target.save(user);

        // 记录方法调用日志：参数和耗时
        LogUtils.info("UserDao.save:%s 耗时%dms", JSON.toJSONString(user), System.currentTimeMillis() - start);

        return ret;
    }

    @Override
    public List<User> listAll() {
        long start = System.currentTimeMillis();

        // 调用目标对象
        List<User> list = target.listAll();

        // 记录方法调用日志：参数和耗时
        LogUtils.info("UserDao.list 耗时%dms", System.currentTimeMillis() - start);

        return list;
    }
}
