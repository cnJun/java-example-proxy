package com.sdk4.javaexample.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AspectForXml {
    public void before() {
        System.out.println("Xml配置 - 前置通知 - 方法调用前执行");
    }

    public void after() {
        System.out.println("Xml配置 - 后置通知 - 方法调用后执行");
    }

    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Xml配置 - 环绕通知 - 方法调用前");

        Object ret;
        try {
            ret = point.proceed();
        } catch (Throwable t) {
            // 注：如果使用了，@AfterThrowing 异常通知，这里未抛出异常的话，异常通知将不会执行
            // t.printStackTrace();
            throw t;
        }

        System.out.println("Xml配置 - 环绕通知 - 方法调用后");

        return ret;
    }

    public void afterReturning(Object ret) {
        System.out.println("Xml配置 - 返回通知 - " + ret);
    }

    public void afterThrowing(Throwable err) {
        System.out.println("Xml配置 - 异常通知 - " + err);
    }
}
