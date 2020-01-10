package com.sdk4.javaexample.dao;

import com.sdk4.javaexample.entity.User;

import java.util.List;

/**
 * 用户信息接口
 */
public interface UserDao {
    /**
     * 保存用信息
     *
     * @param user
     * @return
     */
    boolean save(User user);

    /**
     * 获取所有用户信息
     *
     * @return
     */
    List<User> listAll();
}
