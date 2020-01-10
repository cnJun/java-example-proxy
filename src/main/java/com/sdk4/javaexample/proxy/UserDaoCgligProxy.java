package com.sdk4.javaexample.proxy;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.util.LogUtils;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserDaoCgligProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();

        // 通过反射调用目标对象
        Object ret = methodProxy.invokeSuper(o, objects);

        // 记录方法调用日志：参数和耗时
        LogUtils.info("%s.%s:%s 耗时%dms",
                o.getClass().getSuperclass().getName(),
                method.getName(),
                objects == null || objects.length == 0 ? "" : JSON.toJSONString(objects),
                System.currentTimeMillis() - start);

        return ret;
    }
}
