package com.sdk4.javaexample.proxy;

import com.alibaba.fastjson.JSON;
import com.sdk4.javaexample.util.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CallLogInvocationHandler implements InvocationHandler {
    // 目标对象
    private Object target;

    public CallLogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();

        // 通过反射调用目标对象
        Object ret = method.invoke(target, args);

        // 记录方法调用日志：参数和耗时
        LogUtils.info("%s.%s:%s 耗时%dms",
                target.getClass().getName(),
                method.getName(),
                args == null || args.length == 0 ? "" : JSON.toJSONString(args),
                System.currentTimeMillis() - start);

        return ret;
    }
}
