package com.sdk4.javaexample.dao.impl;

import com.sdk4.javaexample.dao.UserDao;
import com.sdk4.javaexample.entity.User;
import com.sdk4.javaexample.util.JdbcTool;

import java.util.List;

/**
 * 用户信息接口实现
 */
public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User user) {
        return new JdbcTool().save(user);
    }

    @Override
    public List<User> listAll() {
        return new JdbcTool().listAll(User.class);
    }
}
