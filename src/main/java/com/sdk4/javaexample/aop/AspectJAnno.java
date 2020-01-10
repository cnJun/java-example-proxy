package com.sdk4.javaexample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJAnno {
    // 定义切点为所有类的 sayHello 方法
    // 切点方法不会执行，仅将切点定义条件和通知注解关联
    @Pointcut("execution(* *.sayHello(..))")
    public void pointCut() {
        System.out.println("Aspect注解 - 切点方法");
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("Aspect注解 - 前置通知 - 方法调用前执行");
        // throw new RuntimeException();
    }

    @After("pointCut()")
    public void after() {
        System.out.println("Aspect注解 - 后置通知 - 方法调用后执行");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Aspect注解 - 环绕通知 - 方法调用前");

        Object ret;
        try {
            ret = point.proceed();
        } catch (Throwable t) {
            // 注：如果使用了，@AfterThrowing 异常通知，这里未抛出异常的话，异常通知将不会执行
            // t.printStackTrace();
            throw t;
        }

        System.out.println("Aspect注解 - 环绕通知 - 方法调用后");

        return ret;
    }

    @AfterReturning(pointcut = "pointCut()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) {
        System.out.println("Aspect注解 - 返回通知 - " + ret);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "err")
    public void afterThrowing(JoinPoint joinPoint, Throwable err) {
        System.out.println("Aspect注解 - 异常通知 - " + err);
    }

}
