package com.sdk4.javaexample.test;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.dao.UserDao;
import com.sdk4.javaexample.dao.impl.UserDaoImpl;
import com.sdk4.javaexample.entity.User;
import com.sdk4.javaexample.util.ProxyUtils;
import net.sf.cglib.core.DebuggingClassWriter;

import java.util.List;
import java.util.Random;

/**
 * Cglib代理
 */
public class DaoCglibProxyMain {

    public static void main(String[] args) {
        // 指定生成的代理类本地保存位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".");

        // 代理对象
        UserDao daoCglibProxy = ProxyUtils.createCglibProxy(UserDaoImpl.class);

        // 使用目标对象 dao 的地方，全部改为使用代理对象 daoCglibProxy，通过代理对象实现对目标对象的调用
        User user = new User();
        user.setName("SH-" + System.currentTimeMillis());
        user.setAge(new Random().nextInt(100));
        daoCglibProxy.save(user);

        List<User> list = daoCglibProxy.listAll();
        System.out.println(JSON.toJSONString(list, true));
    }

}
