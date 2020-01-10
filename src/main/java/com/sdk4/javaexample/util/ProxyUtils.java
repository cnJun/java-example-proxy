package com.sdk4.javaexample.util;

import com.sdk4.javaexample.proxy.CallLogInvocationHandler;
import com.sdk4.javaexample.proxy.UserDaoCgligProxy;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class ProxyUtils {
    public static <T> T createJdkProxy(Object object) {
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new CallLogInvocationHandler(object));
    }

    public static <T> T createCglibProxy(Class cls){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new UserDaoCgligProxy());
        return (T) enhancer.create();
    }
}
